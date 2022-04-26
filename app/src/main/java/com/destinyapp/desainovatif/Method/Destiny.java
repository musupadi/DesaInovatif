package com.destinyapp.desainovatif.Method;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Destiny {
    public String Tanggal(Spinner Tahun,Spinner Bulan,Spinner Hari){
        String Tanggal = Tahun.getSelectedItem().toString()+"-"+Bulan.getSelectedItem().toString()+"-"+Hari.getSelectedItem().toString();
        return Tanggal;
    }
    public String TTL(EditText TTL, Spinner Tahun, Spinner Bulan, Spinner Hari){
        String TempatTanggalLahir=TTL.getText()+", "+Tahun.getSelectedItem().toString()+"-"+Bulan.getSelectedItem().toString()+"-"+Hari.getSelectedItem().toString();
        return TempatTanggalLahir;
    }
    public void SpinnerHari(Spinner sp, ArrayList<String> arrayList,Context ctx){
        for (int i=1;i<=31;i++){
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(ctx,  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
    }


    public void SpinnerBulan(Spinner sp, ArrayList<String> arrayList,Context ctx){
        for (int i=1;i<=31;i++){
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(ctx,  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
    }

    public void SpinnerTahun(Spinner sp, ArrayList<String> arrayList,Context ctx){
        for (int i=1950;i<=2022;i++){
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(ctx,  android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
    }
    public String Kunci(){
        String Key = "hazxclkadkSA0Ijsad20sl02335sjlso20";
        return Key;
    }
    public String AUTH(){
        String username = "admin_kampung_db_key";
        String password = "hp-15db000wm5asus4320xmsigl4450sonyxz1premium";

        String base = username+":"+password;

        String authHeader = "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        return authHeader;
    }
    public String Changer(String html){
        String replace = html.replace("&#8211;","-");
        String replace2 = replace.replace("&#8216;","'");
        String replace3 = replace2.replace("&#8217;","'");
        String replace4 = replace3.replace("&#8220;","\"");
        String replace5 = replace4.replace("&#8221;","\"");
        return replace5;
    }
    public String FilterTextToJava(String text){
        String replaces = text.replace("</p>\\r\\n<ol>\\r\\n<li>","");
        String replace1 = replaces.replace("<p>","");
        String replace2 = replace1.replace("</p>","");
        String replace3 = replace2.replace("<span style=\"color: #ff6600;\">","");
        String replace4 = replace3.replace("</span>","");
        String replace5 = replace4.replace("<strong>","");
        String replace6 = replace5.replace("</strong>","");
        String replace7 = replace6.replace("<ol>","");
        String replace8 = replace7.replace("</ol>","");
        String replace9 = replace8.replace("<li>","");
        String replace10 = replace9.replace("</li>","");
        String replace11 = replace10.replace("<ul>","");
        String replace12 = replace11.replace("</ul>","");
        String replace13 = replace12.replace("\\n\\n","\\n");
        String replace14 = replace13.replace("<div>","");
        String replace15 = replace14.replace("</div>","");
        String replace16 = replace15.replace("<p>1.","");
        String replace17 = replace16.replace("<p style=\"text-align: center;\">","");
        String replace18 = replace17.replace("<p style=\"text-align: left;\">","");
        String replace19 = replace18.replace("<p style=\"text-align: right;\">","");
        String replace20 = replace19.replace("<a href=","");
        String replace21 = replace20.replace("\"http://bogor-kita.com\">","");
        String replace22 = replace21.replace("</a>","");
        String replace23 = replace22.replace("&#8211","");
        String replace24 = replace23.replace("\"http://BOGOR-KITA.com\">","");
        return replace24;
    }
    public String ChangeNumToInt(String num){
        String replaces = num.replace(".","");
        String replace2 = replaces.replace(",","");
        String replace3 = replace2.replace("Rp ","");
        return replace3;
    }
    public String MagicRP(double nilai){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        BigDecimal bd1 = new BigDecimal(nilai).setScale(0, RoundingMode.HALF_UP);
        String replace = formatRupiah.format(bd1).replace("Rp","Rp ");
        return replace;
    }
    public String MagicNumber(double nilai){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        BigDecimal bd1 = new BigDecimal(nilai).setScale(0, RoundingMode.HALF_UP);
        String replace = formatRupiah.format(bd1).replace("Rp","");
        return replace;
    }
    public void AutoLogin(String username, String password, Context ctx){
        DB_Helper dbHelper = new DB_Helper(ctx);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(AUTH(),Kunci(),username,password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        dbHelper.Logout();
//                        dbHelper.SaveUser(response.body().getData().get(0).getUsernameUser(),password,response.body().getData().get(0).getNamaUser(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getFotoUser(),response.body().getData().get(0).getEmailUser(),response.body().getData().get(0).getNoTelp());
                    }else{
                        Toast.makeText(ctx, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ctx, "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
    public String BASE_URL(){
        String BASE_URL = "https://desabanjarwaru.id/";
        return BASE_URL;
    }
    public String AUTH(String auth){
        String authHeader = "Bearer "+auth;
        return authHeader;
    }
    public String getToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String thisDay = dateFormat.format(date);
        String today = dayName(thisDay,"dd/MM/yyyy");
        String HariIni = "Senin";
        if(today.equals("Monday")){
            HariIni = "Senin";
        }else if(today.equals("Tuesday")){
            HariIni = "Selasa";
        }else if(today.equals("Wednesday")){
            HariIni = "Rabu";
        }else if(today.equals("Thursday")){
            HariIni = "Kamis";
        }else if(today.equals("Friday")){
            HariIni = "Jumat";
        }else if(today.equals("Saturday")){
            HariIni = "Sabtu";
        }else if(today.equals("Sunday")){
            HariIni = "Minggu";
        }
        return HariIni;
    }
    public String DateChanges(String year, String month, String day){
        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        String Dates = day+"-"+MONTH+"-"+year;
        return Dates;
    }
    public String Dates(String dates){
        String result = "Nope";
        String year = dates.substring(0,4);
        String month = dates.substring(5,7);
        String day = dates.substring(8,10);
        String date= year+"-"+month+"-"+day;
        if (date.equals("0000-00-00")){
            result = "Nope";
        }else{
            result = "Yup";
        }
        return result;
    }
    public String MagicDateChange(String dates){
        String result = "";
        String year = dates.substring(0,4);
        String month = dates.substring(5,7);
        String day = dates.substring(8,10);

        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        result = day+" "+MONTH+" "+year;
        return result;

    }
    public static String getThisDayDB(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String Day = dateFormat1.format(date);
        String month = dateFormat2.format(date);
        String Year = dateFormat3.format(date);
        String dates = Year+"-"+month+"-"+Day;
        return dates;
    }
    public static String thisDay(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String Day = dateFormat1.format(date);
        String month = dateFormat2.format(date);
        String Year = dateFormat3.format(date);
        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        return Day+"-"+MONTH+"-"+Year;
    }
    public static String dayName(String inputDate, String format){
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }
    public String SmallDescription(String description){
        String Des = description;
        if (description.length() >= 150){
            Des = description.substring(0,150)+"...";
        }
        return Des;
    }
}
