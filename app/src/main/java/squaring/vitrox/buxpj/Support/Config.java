package squaring.vitrox.buxpj.Support;

import okhttp3.MediaType;

public class Config {

    public static final String REST_BASEURL="https://api.beta.getbux.com/core/16/";
    public static final String SOCKET_BASEURL="https://rtf.beta.getbux.com/subscriptions/me";
    public static final String AUTHORIZATION= "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6IjJlMDcxNjllLTNlMTMtNGFjOS04M2JkLTFiN2I5ZTYwZGM2OSIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInNjcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwibmJmIjoxNDczOTQyMzk0LCJleHAiOjE1MDU0NzgzOTQsImlhdCI6MTQ3Mzk0MjM5NCwianRpIjoiMzgyNTVlNmMtMTU0MC00OGJkLWIxMjUtZWVkMmYyZjRjZDdlIiwiY2lkIjoiODQ3MzYyMjkzNCJ9.1r55eMkL7s2kBMio9KqHqS7NYHdBMT7LAJOFc2U08Lg";
    public static final String LANGUAGE_HEADER="nl-NL,en;q=0.8";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
}