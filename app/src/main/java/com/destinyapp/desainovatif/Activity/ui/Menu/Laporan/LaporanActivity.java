package com.destinyapp.desainovatif.Activity.ui.Menu.Laporan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Adapter.AdapterKategoriLaporan;
import com.destinyapp.desainovatif.Adapter.AdapterKategoriSurat;
import com.destinyapp.desainovatif.BuildConfig;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_Desa;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Dialog dialog;
    Button Permintaan,Submit,Tutup;
    EditText NamaLaporan,DeskripsiLaporan;
    Spinner spinner;
    Button submit;
    TextView kat;
    //Gambar1
    Button btnGallery1,Tambah1;
    Boolean Gallerys1 = false;
    TextView tvGallery1;
    ImageView Gallery1;
    //Gambar2
    LinearLayout linearGambar2;
    Button btnGallery2,Tambah2;
    Boolean Gallerys2 = false;
    TextView tvGallery2;
    ImageView Gallery2;
    //Gambar3
    LinearLayout linearGambar3;
    Button btnGallery3,Tambah3;
    Boolean Gallerys3 = false;
    TextView tvGallery3;
    ImageView Gallery3;
    //Gambar4
    LinearLayout linearGambar4;
    Button btnGallery4;
    Boolean Gallerys4 = false;
    TextView tvGallery4;
    ImageView Gallery4;
    Button close;
    String Price;
    //Dellaroy Logic
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postGallery1 = "";
    String postGallery2 = "";
    String postGallery3 = "";
    String postGallery4 = "";
    int GalleryNum = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        spinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.btnSubmit);
        kat = findViewById(R.id.tvKat);
        NamaLaporan = findViewById(R.id.etNamaLaporan);
        DeskripsiLaporan = findViewById(R.id.etDeskripsiLaporan);
        //Gallery 1
        btnGallery1 = findViewById(R.id.btnUploadGallery1);
        Tambah1 = findViewById(R.id.btnTambah1);
        tvGallery1 = findViewById(R.id.tvGambarGallery1);
        Gallery1 = findViewById(R.id.ivGambarGallery1);
        //Gallery 2
        linearGambar2 = findViewById(R.id.linearGallery2);
        btnGallery2 = findViewById(R.id.btnUploadGallery2);
        Tambah2 = findViewById(R.id.btnTambah2);
        tvGallery2 = findViewById(R.id.tvGambarGallery2);
        Gallery2 = findViewById(R.id.ivGambarGallery2);
        //Gallery 3
        linearGambar3 = findViewById(R.id.linearGallery3);
        btnGallery3 = findViewById(R.id.btnUploadGallery3);
        Tambah3 = findViewById(R.id.btnTambah3);
        tvGallery3 = findViewById(R.id.tvGambarGallery3);
        Gallery3 = findViewById(R.id.ivGambarGallery3);
        //Gallery 4
        linearGambar4 = findViewById(R.id.linearGallery4);
        btnGallery4 = findViewById(R.id.btnUploadGallery4);
        tvGallery4 = findViewById(R.id.tvGambarGallery4);
        Gallery4 = findViewById(R.id.ivGambarGallery4);

        dbHelper = new DB_Helper(LaporanActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
            }
        }
        GetKategori();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                String clickedItems = clickedItem.getId_laporan_kategori();
                kat.setText(clickedItems);
                Toast.makeText(LaporanActivity.this, clickedItems, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        OnClick();
    }
    private void Logic1(){
        final ProgressDialog pd = new ProgressDialog(LaporanActivity.this);
        pd.setMessage("Sedang Mengirimkan Laporan");
        pd.show();
        pd.setCancelable(false);

        //File 1
        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery1.getName(), fileReqBodyGallery1);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.PostLaporan1(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),ID_Desa),
                RequestBody.create(MediaType.parse("text/plain"),kat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NamaLaporan.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),DeskripsiLaporan.getText().toString()),
                partGallery1);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(LaporanActivity.this, "Laporan berhasil Terkirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LaporanActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LaporanActivity.this, "Laporan Tidak berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(LaporanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic2(){
        final ProgressDialog pd = new ProgressDialog(LaporanActivity.this);
        pd.setMessage("Sedang Mengirimkan Laporan");
        pd.show();
        pd.setCancelable(false);

        //File 1
        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery1.getName(), fileReqBodyGallery1);

        //File 2
        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery2.getName(), fileReqBodyGallery2);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.PostLaporan2(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),ID_Desa),
                RequestBody.create(MediaType.parse("text/plain"),kat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NamaLaporan.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),DeskripsiLaporan.getText().toString()),
                partGallery1,
                partGallery2);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(LaporanActivity.this, "Laporan berhasil Terkirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LaporanActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LaporanActivity.this, "Laporan Tidak berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(LaporanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic3(){
        final ProgressDialog pd = new ProgressDialog(LaporanActivity.this);
        pd.setMessage("Sedang Mengirimkan Laporan");
        pd.show();
        pd.setCancelable(false);

        //File 1
        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery1.getName(), fileReqBodyGallery1);

        //File 2
        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery2.getName(), fileReqBodyGallery2);

        //File 3
        File fileGallery3 = new File(postGallery3);
        RequestBody fileReqBodyGallery3 = RequestBody.create(MediaType.parse("image/*"), fileGallery3);
        MultipartBody.Part partGallery3 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery3.getName(), fileReqBodyGallery3);


        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.PostLaporan3(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),ID_Desa),
                RequestBody.create(MediaType.parse("text/plain"),kat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NamaLaporan.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),DeskripsiLaporan.getText().toString()),
                partGallery1,
                partGallery2,
                partGallery3);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(LaporanActivity.this, "Laporan berhasil Terkirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LaporanActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LaporanActivity.this, "Laporan Tidak berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(LaporanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic4(){
        final ProgressDialog pd = new ProgressDialog(LaporanActivity.this);
        pd.setMessage("Sedang Mengirimkan Laporan");
        pd.show();
        pd.setCancelable(false);

        //File 1
        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery1.getName(), fileReqBodyGallery1);

        //File 2
        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery2.getName(), fileReqBodyGallery2);

        //File 3
        File fileGallery3 = new File(postGallery3);
        RequestBody fileReqBodyGallery3 = RequestBody.create(MediaType.parse("image/*"), fileGallery3);
        MultipartBody.Part partGallery3 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery3.getName(), fileReqBodyGallery3);

        //File 4
        File fileGallery4 = new File(postGallery4);
        RequestBody fileReqBodyGallery4 = RequestBody.create(MediaType.parse("image/*"), fileGallery4);
        MultipartBody.Part partGallery4 = MultipartBody.Part.createFormData("foto_laporan[]", fileGallery4.getName(), fileReqBodyGallery4);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.PostLaporan4(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),ID_Desa),
                RequestBody.create(MediaType.parse("text/plain"),kat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NamaLaporan.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),DeskripsiLaporan.getText().toString()),
                partGallery1,
                partGallery2,
                partGallery3,
                partGallery4);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(LaporanActivity.this, "Laporan berhasil Terkirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LaporanActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LaporanActivity.this, "Laporan Tidak berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(LaporanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void OnClick(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GalleryNum==1){
                    Logic1();
                }else if(GalleryNum==2){
                    Logic2();
                }else if(GalleryNum==3){
                    Logic3();
                }else{
                    Logic4();
                }
            }
        });
        //Tambah
        Tambah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGambar2.setVisibility(View.VISIBLE);
                Tambah1.setVisibility(View.GONE);
                GalleryNum=2;
            }
        });
        Tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGambar3.setVisibility(View.VISIBLE);
                Tambah2.setVisibility(View.GONE);
                GalleryNum=3;
            }
        });
        Tambah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGambar4.setVisibility(View.VISIBLE);
                Tambah3.setVisibility(View.GONE);
                GalleryNum=4;
            }
        });
        //Gallery1
        btnGallery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(LaporanActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gallerys1 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        Gallery1.setVisibility(View.VISIBLE);
                                        tvGallery1.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gallerys1= true;
                                        captureImage();
                                        Gallery1.setVisibility(View.VISIBLE);
                                        tvGallery1.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Gallery1.setImageResource(R.drawable.ic_launcher_background);
                                        Gallery1.setVisibility(View.GONE);
                                        tvGallery1.setVisibility(View.GONE);
                                        Gallerys1 = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gallery2
        btnGallery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(LaporanActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gallerys2 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        Gallery2.setVisibility(View.VISIBLE);
                                        tvGallery2.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gallerys2= true;
                                        captureImage();
                                        Gallery2.setVisibility(View.VISIBLE);
                                        tvGallery2.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Gallery2.setImageResource(R.drawable.ic_launcher_background);
                                        Gallery2.setVisibility(View.GONE);
                                        tvGallery2.setVisibility(View.GONE);
                                        Gallerys2 = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gallery3
        btnGallery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(LaporanActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gallerys3 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        Gallery3.setVisibility(View.VISIBLE);
                                        tvGallery3.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gallerys3= true;
                                        captureImage();
                                        Gallery3.setVisibility(View.VISIBLE);
                                        tvGallery3.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Gallery3.setImageResource(R.drawable.ic_launcher_background);
                                        Gallery3.setVisibility(View.GONE);
                                        tvGallery3.setVisibility(View.GONE);
                                        Gallerys3 = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gallery4
        btnGallery4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(LaporanActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gallerys4 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        Gallery4.setVisibility(View.VISIBLE);
                                        tvGallery4.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gallerys4= true;
                                        captureImage();
                                        Gallery4.setVisibility(View.VISIBLE);
                                        tvGallery4.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Gallery4.setImageResource(R.drawable.ic_launcher_background);
                                        Gallery4.setVisibility(View.GONE);
                                        tvGallery4.setVisibility(View.GONE);
                                        Gallerys4 = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
    }
    private void GetKategori(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.Laporan_Kategori("2");
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterKategoriLaporan adapter = new AdapterKategoriLaporan(LaporanActivity.this,mItems);
                    spinner.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(LaporanActivity.this, "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LaporanActivity.this,"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Dellaroy Logic
    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            // We give some instruction to the intent to save the image
            File photoFile = null;

            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile();
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            // The following strings calls the camera app and wait for his file in return.
            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }
    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gallerys1){
                    postGallery1 = mediaPath;
                    String filename=postGallery1.substring(postGallery1.lastIndexOf("/")+1);
                    Gallery1.setVisibility(View.VISIBLE);
                    tvGallery1.setVisibility(View.VISIBLE);
                    Gallery1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery1.setText(filename);
                    Gallerys1=false;
                    Toast.makeText(LaporanActivity.this, filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys2){
                    postGallery2 = mediaPath;
                    String filename=postGallery2.substring(postGallery2.lastIndexOf("/")+1);
                    Gallery2.setVisibility(View.VISIBLE);
                    tvGallery2.setVisibility(View.VISIBLE);
                    Gallery2.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery2.setText(filename);
                    Gallerys2=false;
                    Toast.makeText(LaporanActivity.this, filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys3){
                    postGallery3 = mediaPath;
                    String filename=postGallery3.substring(postGallery3.lastIndexOf("/")+1);
                    Gallery3.setVisibility(View.VISIBLE);
                    tvGallery3.setVisibility(View.VISIBLE);
                    Gallery3.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery3.setText(filename);
                    Gallerys3=false;
                    Toast.makeText(LaporanActivity.this, filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys4){
                    postGallery4 = mediaPath;
                    String filename=postGallery4.substring(postGallery4.lastIndexOf("/")+1);
                    Gallery4.setVisibility(View.VISIBLE);
                    tvGallery4.setVisibility(View.VISIBLE);
                    Gallery4.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery4.setText(filename);
                    Gallerys4=false;
                    Toast.makeText(LaporanActivity.this, filename, Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if(Gallerys1){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(Gallery1);
                    postGallery1 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(Gallery1);
                    postGallery1 = fileUri.getPath();
                }
                String filename=postGallery1.substring(postGallery1.lastIndexOf("/")+1);
                Gallery1.setVisibility(View.VISIBLE);
                tvGallery1.setVisibility(View.VISIBLE);
                tvGallery1.setText(filename);
                Gallerys1=false;
            }else if(Gallerys2){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(Gallery2);
                    postGallery2 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(Gallery2);
                    postGallery2 = fileUri.getPath();
                }
                String filename=postGallery2.substring(postGallery2.lastIndexOf("/")+1);
                Gallery2.setVisibility(View.VISIBLE);
                tvGallery2.setVisibility(View.VISIBLE);
                tvGallery2.setText(filename);
                Gallerys2=false;
            }else if(Gallerys3){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(Gallery3);
                    postGallery3 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(Gallery3);
                    postGallery3 = fileUri.getPath();
                }
                String filename=postGallery3.substring(postGallery3.lastIndexOf("/")+1);
                Gallery3.setVisibility(View.VISIBLE);
                tvGallery3.setVisibility(View.VISIBLE);
                tvGallery3.setText(filename);
                Gallerys3=false;
            }else if(Gallerys4){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(Gallery4);
                    postGallery4 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(Gallery4);
                    postGallery4 = fileUri.getPath();
                }
                String filename=postGallery4.substring(postGallery4.lastIndexOf("/")+1);
                Gallery4.setVisibility(View.VISIBLE);
                tvGallery4.setVisibility(View.VISIBLE);
                tvGallery4.setText(filename);
                Gallerys4=false;
            }
        }
    }
}