package com.destinyapp.desainovatif.Activity.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Laporan.LaporanActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    ImageView Picture;
    TextView nama,email,pekerjaan,saldo;
    DB_Helper dbHelper;
    Dialog myDialog;
    EditText oldPassword,newPassword,newPassword2,saran;
    String Username,Password,Nama,Photo,Email,ID;
    Destiny destiny;
    Button submit;
    Button logout,Laporan,UbahPassword;


    //Dellaroy Logic
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;


    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postFoto1= "";
    String postFoto2= "";
    String postFoto3= "";
    String postFoto4= "";
    //ONCLICK
    Boolean Gambar1 = false;
    Boolean Gambar2 = false;
    Boolean Gambar3 = false;
    Boolean Gambar4 = false;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.tvNama);
        saldo = view.findViewById(R.id.tvSaldo);
        pekerjaan = view.findViewById(R.id.tvPekerjaan);
        email = view.findViewById(R.id.tvEmail);
        Picture = view.findViewById(R.id.ivProfile);
        logout = view.findViewById(R.id.btnLogot);
        Laporan = view.findViewById(R.id.btnLaporan);
        UbahPassword = view.findViewById(R.id.btnUbahPassword);

        //Dialog Change Password
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_change_password);
        oldPassword=myDialog.findViewById(R.id.etPasswordOld);
        newPassword=myDialog.findViewById(R.id.etPassword);
        newPassword2=myDialog.findViewById(R.id.etPassword2);
        submit=myDialog.findViewById(R.id.changePassword);

        dbHelper = new DB_Helper(getActivity());
        destiny = new Destiny();
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
            }
        }
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> User = api.user_detail(destiny.AUTH(),destiny.Kunci(),ID);
        User.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (!Photo.isEmpty()){
                    Glide.with(getActivity())
                            .load(destiny.BASE_URL()+Photo)
                            .into(Picture);
                }
                nama.setText(response.body().getData().getNama_user());
                email.setText(response.body().getData().getEmail_user());
                pekerjaan.setText(response.body().getData().getPekerjaan_user());
                saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imam_Kontol();
            }
        });

        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LaporanActivity.class);
                startActivity(intent);
            }
        });
        UbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Kata Sandi Lama Kosong", Toast.LENGTH_SHORT).show();
                }else if (newPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Kata Sandi Baru Kosong", Toast.LENGTH_SHORT).show();
                }else if(newPassword2.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Konfirmasi Kata Sandi baru Kosong", Toast.LENGTH_SHORT).show();
                }else if(!newPassword.getText().toString().equals(newPassword2.getText().toString())){
                    Toast.makeText(getActivity(), "Kata Sandi baru dan Konfirmasi Kata Sandi Baru Berbeda", Toast.LENGTH_SHORT).show();
                }else{
                    ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                    Call<ResponseModel> logic = api.ChangePassword(destiny.AUTH(),
                            "hazxclkadkSA0Ijsad20sl02335sjlso20",
                            ID,
                            oldPassword.getText().toString(),
                            newPassword2.getText().toString(),
                            newPassword.getText().toString());
                    logic.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.body().getMessage().equals("sukses")){
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                myDialog.hide();
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.Logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Akun Anda Telah Berhasil Keluar", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
            }
        });
    }
    private void Imam_Kontol(){
        Gambar1 = true;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
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
                Log.d("TEST", "Oops! Failed create "
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

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gambar1) {
                    postFoto1 = mediaPath;
                    String filename = postFoto1.substring(postFoto1.lastIndexOf("/") + 1);

                    Gambar1 = false;
                    final ProgressDialog pd = new ProgressDialog(getActivity());
                    pd.setMessage("Sedang Mengisi Post");
                    pd.show();
                    pd.setCancelable(false);
                    File file1= new File(postFoto1);
                    RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_profil", file1.getName(), fileReqBody1);
                    Call<Ress> datas;
                    ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                    datas =api.EditFoto(
                            destiny.AUTH(),
                            RequestBody.create(MediaType.parse("text/plain"),destiny.Kunci()),
                            RequestBody.create(MediaType.parse("text/plain"),ID),
                            Foto1);
                    datas.enqueue(new Callback<Ress>() {
                        @Override
                        public void onResponse(Call<Ress> call, Response<Ress> response) {
                            pd.hide();
                            try {
                                Toast.makeText(getActivity(), "Gambar Berhasil Diubah", Toast.LENGTH_SHORT).show();
                                Picture.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                            }catch (Exception e){
                                Log.d("Zyarga : ",e.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<Ress> call, Throwable t) {

                        }
                    });
                }
            }}
    }
}