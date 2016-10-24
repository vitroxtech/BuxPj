package squaring.vitrox.buxpj;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import squaring.vitrox.buxpj.DependencyInjection.Component.ActivityComponent;
import squaring.vitrox.buxpj.DependencyInjection.Component.DaggerActivityComponent;
import squaring.vitrox.buxpj.Model.Product;
import squaring.vitrox.buxpj.Network.RestApiService;
import squaring.vitrox.buxpj.Support.DataListHelper;


public class MainActivity extends AppCompatActivity {

    private ActivityComponent mComponent;
    private static final String TAG = "MAIN_ACTIVITY";

    @BindView(R.id.button)
    Button mButtonGo;
    @BindView(R.id.spinnerProducts)
    Spinner mSpinner;

    @BindView(R.id.testmessage)
    TextView showmessage;

    @Inject
    DataListHelper datalist;

    @Inject
    RestApiService service;


    private String selectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();
        mComponent.inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setData(this);
        mButtonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final ProgressDialog progress = ProgressDialog.show(MainActivity.this, "Loading",
                        "getting details", true);
                service.getJsonDetail(selectedId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<Product>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "RestAPI_DONE");
                                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                                startActivity(intent);
                                progress.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                progress.dismiss();
                                SendErrorMessage(e.getMessage());

                            }

                            @Override
                            public void onNext(Product product) {
                                getApp().setProduct(product);

                            }
                        });
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedId = datalist.getIdIndexOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedId = datalist.getIdIndexOf(0);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void setData(Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datalist.getNameList());
        mSpinner.setAdapter(adapter);
    }

    private void SendErrorMessage(final String txt) {
        //Show errors with Snackbar
        this.runOnUiThread(new Runnable() {
            public void run() {
                Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    protected App getApp() {
        return (App) getApplicationContext();
    }


}
