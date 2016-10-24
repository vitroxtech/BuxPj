package squaring.vitrox.buxpj;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.http.Body;
import squaring.vitrox.buxpj.DependencyInjection.Component.ActivityComponent;
import squaring.vitrox.buxpj.DependencyInjection.Component.DaggerActivityComponent;
import squaring.vitrox.buxpj.Model.Product;
import squaring.vitrox.buxpj.Model.SocketMessage;
import squaring.vitrox.buxpj.Network.SocketConnection;
import squaring.vitrox.buxpj.Network.socketConnectionListener;
import squaring.vitrox.buxpj.Support.PriceHelper;


public class DetailsActivity extends AppCompatActivity implements socketConnectionListener {
    private ActivityComponent mComponent;
    @BindView(R.id.titleDetail)
    TextView titleDetail;
    @BindView(R.id.currentPText)
    TextView currentPrice;
    @BindView(R.id.closingPText)
    TextView closingPrice;
    @BindView(R.id.symbolDetail)
    TextView symbolDetail;
    @BindView(R.id.percentText)
    TextView percentDifference;

    @Inject
    SocketConnection mSocketConnection;

    private Product myProduct;
    private PriceHelper PH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();
        mComponent.inject(this);

        ButterKnife.bind(this);
        myProduct = getApp().getProduct();
        setupDetails();
        try {
            mSocketConnection.setMesageparams(myProduct.getSecurityId());
            mSocketConnection.CreateConnection();

        } catch (IOException e) {
            SendErrorMessage(e.getMessage());
        }
        mSocketConnection.addListener(DetailsActivity.this);
    }

    protected App getApp() {
        return (App) getApplicationContext();
    }

    private void setupDetails() {
        PH = new PriceHelper();
        titleDetail.setText(myProduct.getDisplayName());
        currentPrice.setText(PH.getshowablePrice(myProduct.getCurrentPrice().getAmount(), myProduct.getCurrentPrice().getCurrency(), myProduct.getCurrentPrice().getDecimals()));
        closingPrice.setText(PH.getshowablePrice(myProduct.getClosingPrice().getAmount(), myProduct.getClosingPrice().getCurrency(), myProduct.getClosingPrice().getDecimals()));
        symbolDetail.setText(myProduct.getSymbol());
        percentDifference.setText(PH.getpercentDiff(myProduct.getClosingPrice().getAmount(), myProduct.getCurrentPrice().getAmount(), myProduct.getCurrentPrice().getDecimals()));
    }

    @Override
    public void receivedmessage(final SocketMessage message) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                currentPrice.setText(PH.getshowablePrice(message.getBody().getCurrentPrice(), myProduct.getCurrentPrice().getCurrency(), myProduct.getCurrentPrice().getDecimals()));
                percentDifference.setText(PH.getpercentDiff(myProduct.getClosingPrice().getAmount(), message.getBody().getCurrentPrice(), myProduct.getCurrentPrice().getDecimals()));
            }
        });

    }

    @Override
    public void onError(String error) {
        SendErrorMessage(error);
    }

    private void SendErrorMessage(final String txt) {
        //Show errors with Snackbar
        this.runOnUiThread(new Runnable() {
            public void run() {
                Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}
