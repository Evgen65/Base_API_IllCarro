package okhttp;

import com.google.gson.Gson;
import dto.CarDto;
import dto.GetMyCarsDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserCarsTestsOkhttp {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc2MjE2ODIxLCJpYXQiOjE2NzU2MTY4MjF9.sS21BSserjCrnnWXdelVzfBuPeOIF4pTbqtPMmefLk8";

    @Test
    public void successGetUserCars() throws IOException {
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/cars/my")
                .header("Authorization",token).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetMyCarsDto carsDto = gson.fromJson(response.body().string(),GetMyCarsDto.class);

        List<CarDto> cars = carsDto.getCars();
        for(CarDto car: cars){
            System.out.println(car.getSerialNumber());
            System.out.println("*****");
        }
    }
}
