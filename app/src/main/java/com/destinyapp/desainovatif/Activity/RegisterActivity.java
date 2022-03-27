package com.destinyapp.desainovatif.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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
import com.destinyapp.desainovatif.Activity.ui.Menu.KemajuanDesa.KemajuanDesaActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Toko.DetailTokoActivity;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterListRT;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterListUserRW;
import com.destinyapp.desainovatif.BuildConfig;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;

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

public class RegisterActivity extends AppCompatActivity {
    Spinner RT,RW;
    TextView IDRT,IDRW;
    Destiny destiny = new Destiny();
    private List<DataModel> mItems = new ArrayList<>();
    LinearLayout login,register;
    EditText Email,Password,ConfirmPassword,Nama,Pekerjaan,NIK,NoHP,Alamat;
    //Gambar1
    Button btnGallery1,Tambah1;
    Boolean Gallerys1 = false;
    TextView tvGallery1;
    ImageView Gallery1;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Declaration();
        getRW();
        OnSelected();
        OnClick();
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
        pd.setMessage("Sedang Mencoba Register");
        pd.setCancelable(false);
        pd.show();

        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_user", fileGallery1.getName(), fileReqBodyGallery1);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        if (postGallery1.isEmpty()){
            Call<Ress> Data = api.Register(
                    destiny.AUTH(),
                    RequestBody.create(MediaType.parse("text/plain"),destiny.Kunci()),
                    RequestBody.create(MediaType.parse("text/plain"),Nama.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Password.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),IDRW.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),IDRT.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),NoHP.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Pekerjaan.getText().toString()));
            Data.enqueue(new Callback<Ress>() {
                @Override
                public void onResponse(Call<Ress> call, Response<Ress> response) {
                    pd.hide();
                    try {
                        if (response.body().getMessage().equals("Pendaftaran Berhasil, mohon untuk login")){
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent );
                        }else{
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Ress> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Call<Ress> Data = api.Register(
                    destiny.AUTH(),
                    RequestBody.create(MediaType.parse("text/plain"),destiny.Kunci()),
                    RequestBody.create(MediaType.parse("text/plain"),Nama.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Password.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),IDRW.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),IDRT.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),NoHP.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Pekerjaan.getText().toString()),
                    partGallery1);
            Data.enqueue(new Callback<Ress>() {
                @Override
                public void onResponse(Call<Ress> call, Response<Ress> response) {
                    pd.hide();
                    try {
                        if (response.body().getMessage().equals("Pendaftaran Berhasil, mohon untuk login")){
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent );
                        }else{
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Ress> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    private void Check(){
        if (Password.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (ConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Konfirmasi Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Nama.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Pekerjaan.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Pekerjaan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (NoHP.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "No HP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Alamat.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (!Password.getText().toString().equals(ConfirmPassword.getText().toString())){
            Toast.makeText(RegisterActivity.this, "Password dan Konfirmasi Password tidak sama", Toast.LENGTH_SHORT).show();
        }else{
            Logic();
        }
    }
    private void OnSelected(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });
        RW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_rw());
                    IDRW.setText(String.valueOf(clickedItems));
                    getRT();
                }catch (Exception e){
                    Toast.makeText(RegisterActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Zyarga Code : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        RT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_rt());
                    IDRT.setText(String.valueOf(clickedItems));
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void OnClick(){
        btnGallery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(RegisterActivity.this)
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
    }
    private void Declaration(){
        //Spinner
        RT=findViewById(R.id.spRT);
        RW=findViewById(R.id.spRW);
        IDRT=findViewById(R.id.tvIdRT);
        IDRW=findViewById(R.id.tvIdRW);
        //Edit Text
        Email=findViewById(R.id.etEmail);
        Password=findViewById(R.id.etPassword);
        ConfirmPassword=findViewById(R.id.etConfPassword);
        Nama=findViewById(R.id.etNama);
        Pekerjaan=findViewById(R.id.etPekerjaan);
        NIK=findViewById(R.id.etNIK);
        NoHP=findViewById(R.id.etNoHP);
        Alamat=findViewById(R.id.etAlamat);
        //Gallery 1
        btnGallery1 = findViewById(R.id.btnUploadGallery1);
        Tambah1 = findViewById(R.id.btnTambah1);
        tvGallery1 = findViewById(R.id.tvGambarGallery1);
        Gallery1 = findViewById(R.id.ivGambarGallery1);
        //Linear
        register = findViewById(R.id.linearRegister);
    }
    private void getRW(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.PostDaftarRW(destiny.AUTH(),destiny.Kunci());
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterListUserRW adapter = new AdapterListUserRW(RegisterActivity.this,mItems);
                    RW.setAdapter(adapter);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getRT(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.PostDaftarRT(destiny.AUTH(),destiny.Kunci(),IDRW.getText().toString());
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterListRT adapter = new AdapterListRT(RegisterActivity.this,mItems);
                    RT.setAdapter(adapter);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegisterActivity.this, filename, Toast.LENGTH_SHORT).show();
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
            }
        }
    }
}