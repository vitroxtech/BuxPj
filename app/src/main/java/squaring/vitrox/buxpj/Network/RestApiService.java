package squaring.vitrox.buxpj.Network;


import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;
import squaring.vitrox.buxpj.Model.Product;

/**
 * Created by miguelgomez on 10/21/16.
 */

public interface RestApiService {

    @Headers({"Accept-Language: nl-NL,en;q=0.8",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6IjJlMDcxNjllLTNlMTMtNGFjOS04M2JkLTFiN2I5ZTYwZGM2OSIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInNjcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwibmJmIjoxNDczOTQyMzk0LCJleHAiOjE1MDU0NzgzOTQsImlhdCI6MTQ3Mzk0MjM5NCwianRpIjoiMzgyNTVlNmMtMTU0MC00OGJkLWIxMjUtZWVkMmYyZjRjZDdlIiwiY2lkIjoiODQ3MzYyMjkzNCJ9.1r55eMkL7s2kBMio9KqHqS7NYHdBMT7LAJOFc2U08Lg"
    ,"Accept: application/json"})
    @GET("products/{Product}")
    Observable<Product> getJsonDetail(@Path("Product") String product);

}
