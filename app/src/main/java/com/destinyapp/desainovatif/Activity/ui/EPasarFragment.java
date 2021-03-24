package com.destinyapp.desainovatif.Activity.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Adapter.AdapterSurat;
import com.destinyapp.desainovatif.Adapter.AdapterToko;
import com.destinyapp.desainovatif.Adapter.AdapterTokoSaya;
import com.destinyapp.desainovatif.BuildConfig;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
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

public class EPasarFragment extends Fragment {
    Dialog DialogPermintaan;
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_DESA;
    RecyclerView recycler,recyclerMyShop;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Dialog dialog;
    Button Permintaan,Submit,Tutup;
    EditText NamaToko,NoToko,Deskripsi;
    ImageView Gambar1,Gambar2,Gambar3,Gambar4;
    //Cover
    Button btnCover;
    Boolean Covers = false;
    TextView tvCover;
    ImageView Cover;

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
    Button submit,close;
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
    String postFoto= "";
    String postGallery1 = "";
    String postGallery2 = "";
    String postGallery3 = "";
    String postGallery4 = "";
    int GalleryNum = 1;
    public EPasarFragment() {
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
        return inflater.inflate(R.layout.fragment_e_pasar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        recycler = view.findViewById(R.id.recycler);
        recyclerMyShop = view.findViewById(R.id.recyclerTokoSaya);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_DESA = cursor.getString(5);
            }
        }
        Permintaan = view.findViewById(R.id.btnPermintaan);
        Logic();
        TokoSaya();
        DialogPermintaan = new Dialog(getActivity());
        DialogPermintaan.setContentView(R.layout.dialog_permintaan_toko);
        DialogPermintaan.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        DialogPermintaan.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded);
        submit = DialogPermintaan.findViewById(R.id.btnSubmit);
        close = DialogPermintaan.findViewById(R.id.btnClose);
        NamaToko = DialogPermintaan.findViewById(R.id.etNamaToko);
        NoToko = DialogPermintaan.findViewById(R.id.etNoHPToko);
        Deskripsi = DialogPermintaan.findViewById(R.id.etDeskripsiToko);
        btnCover = DialogPermintaan.findViewById(R.id.btnCover);
        Cover = DialogPermintaan.findViewById(R.id.ivCover);
        tvCover = DialogPermintaan.findViewById(R.id.tvCover);
        //Gallery 1
        btnGallery1 = DialogPermintaan.findViewById(R.id.btnUploadGallery1);
        Tambah1 = DialogPermintaan.findViewById(R.id.btnTambah1);
        tvGallery1 = DialogPermintaan.findViewById(R.id.tvGambarGallery1);
        Gallery1 = DialogPermintaan.findViewById(R.id.ivGambarGallery1);
        //Gallery 2
        linearGambar2 = DialogPermintaan.findViewById(R.id.linearGallery2);
        btnGallery2 = DialogPermintaan.findViewById(R.id.btnUploadGallery2);
        Tambah2 = DialogPermintaan.findViewById(R.id.btnTambah2);
        tvGallery2 = DialogPermintaan.findViewById(R.id.tvGambarGallery2);
        Gallery2 = DialogPermintaan.findViewById(R.id.ivGambarGallery2);
        //Gallery 3
        linearGambar3 = DialogPermintaan.findViewById(R.id.linearGallery3);
        btnGallery3 = DialogPermintaan.findViewById(R.id.btnUploadGallery3);
        Tambah3 = DialogPermintaan.findViewById(R.id.btnTambah3);
        tvGallery3 = DialogPermintaan.findViewById(R.id.tvGambarGallery3);
        Gallery3 = DialogPermintaan.findViewById(R.id.ivGambarGallery3);
        //Gallery 4
        linearGambar4 = DialogPermintaan.findViewById(R.id.linearGallery4);
        btnGallery4 = DialogPermintaan.findViewById(R.id.btnUploadGallery4);
        tvGallery4 = DialogPermintaan.findViewById(R.id.tvGambarGallery4);
        Gallery4 = DialogPermintaan.findViewById(R.id.ivGambarGallery4);
        Permintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPermintaan.show();
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
                new MaterialDialog.Builder(getActivity())
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
                new MaterialDialog.Builder(getActivity())
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
                new MaterialDialog.Builder(getActivity())
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
                new MaterialDialog.Builder(getActivity())
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
        btnCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Covers = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        Cover.setVisibility(View.VISIBLE);
                                        tvCover.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Covers = true;
                                        captureImage();
                                        Cover.setVisibility(View.VISIBLE);
                                        tvCover.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Cover.setImageResource(R.drawable.ic_launcher_background);
                                        Cover.setVisibility(View.GONE);
                                        tvCover.setVisibility(View.GONE);
                                        Covers = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GalleryNum==1){
                    if (Checker().equals("1")){
                        Submit1();
                    }
                }else if (GalleryNum==2){
                    if (Checker().equals("1")){
                        Submit2();
                    }
                }else if(GalleryNum==3){
                    if (Checker().equals("1")){
                        Submit3();
                    }
                }else{
                    if (Checker().equals("1")){
                        Submit4();
                    }
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPermintaan.hide();
            }
        });
    }
    private void TokoSaya(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerMyShop.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.MyToko(ID);
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    mAdapter = new AdapterTokoSaya(getActivity(),mItems);
                    recyclerMyShop.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Submit1(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Menyimpan data ke Server");
        pd.setCancelable(false);
        pd.show();
        File file = new File(postFoto);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partCover = MultipartBody.Part.createFormData("fotoCover", file.getName(), fileReqBody);

        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto_toko[]", fileGallery1.getName(), fileReqBodyGallery1);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.
                PostToko(
                        RequestBody.create(MediaType.parse("text/plain"),ID),
                        RequestBody.create(MediaType.parse("text/plain"),NamaToko.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),NoToko.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),ID_DESA),
                        partGallery1);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                try {
                    Toast.makeText(getActivity(), "Data Berhasil Terinput", Toast.LENGTH_SHORT).show();
                    pd.hide();
                    Intent intent  = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Submit2(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Menyimpan data ke Server");
        pd.setCancelable(false);
        pd.show();
        File file = new File(postFoto);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partCover = MultipartBody.Part.createFormData("fotoCover", file.getName(), fileReqBody);

        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery1);

        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery2);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> Data = api.PostToko(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),NamaToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NoToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),ID_DESA),
                partCover,
                partGallery1,
                partGallery2);
        Data.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                try {
                    Toast.makeText(getActivity(), "Data Berhasil Terinput", Toast.LENGTH_SHORT).show();
                    pd.hide();
                    Intent intent  = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Submit3(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Menyimpan data ke Server");
        pd.setCancelable(false);
        pd.show();
        File file = new File(postFoto);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partCover = MultipartBody.Part.createFormData("fotoCover", file.getName(), fileReqBody);

        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery1);

        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery2);

        File fileGallery3 = new File(postGallery3);
        RequestBody fileReqBodyGallery3 = RequestBody.create(MediaType.parse("image/*"), fileGallery3);
        MultipartBody.Part partGallery3 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery3);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> Data = api.PostToko(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),NamaToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NoToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),ID_DESA),
                partCover,
                partGallery1,
                partGallery2,
                partGallery3);
        Data.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                try {
                    Toast.makeText(getActivity(), "Data Berhasil Terinput", Toast.LENGTH_SHORT).show();
                    pd.hide();
                    Intent intent  = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Submit4(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Menyimpan data ke Server");
        pd.setCancelable(false);
        pd.show();
        File file = new File(postFoto);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partCover = MultipartBody.Part.createFormData("fotoCover", file.getName(), fileReqBody);

        File fileGallery1 = new File(postGallery1);
        RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
        MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery1);

        File fileGallery2 = new File(postGallery2);
        RequestBody fileReqBodyGallery2 = RequestBody.create(MediaType.parse("image/*"), fileGallery2);
        MultipartBody.Part partGallery2 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery2);

        File fileGallery3 = new File(postGallery3);
        RequestBody fileReqBodyGallery3 = RequestBody.create(MediaType.parse("image/*"), fileGallery3);
        MultipartBody.Part partGallery3 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery3);

        File fileGallery4 = new File(postGallery4);
        RequestBody fileReqBodyGallery4 = RequestBody.create(MediaType.parse("image/*"), fileGallery4);
        MultipartBody.Part partGallery4 = MultipartBody.Part.createFormData("foto[]", file.getName(), fileReqBodyGallery4);

        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> Data = api.PostToko(
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),NamaToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),NoToko.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),ID_DESA),
                partCover,
                partGallery1,
                partGallery2,
                partGallery3,
                partGallery4);
        Data.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                try {
                    Toast.makeText(getActivity(), "Data Berhasil Terinput", Toast.LENGTH_SHORT).show();
                    pd.hide();
                    Intent intent  = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String Checker(){
        String ok = "1";
        if (NamaToko.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Nama Toko Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            ok="0";
        }else if(NoToko.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Nomor Toko Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            ok="0";
        }else if(Deskripsi.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Deskripsi Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            ok="0";
        }if(postGallery1.isEmpty()){
            Toast.makeText(getActivity(), "Foto Gallery Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            ok="0";
        }
        return ok;
    }
    private void Logic(){
        mManager = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.Toko("","");
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    mAdapter = new AdapterToko(getActivity(),mItems);
                    recycler.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                    getActivity(),
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

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Covers){
                    postFoto = mediaPath;
                    String filename=postFoto.substring(postFoto.lastIndexOf("/")+1);
                    Cover.setVisibility(View.VISIBLE);
                    tvCover.setVisibility(View.VISIBLE);
                    Cover.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvCover.setText(filename);
                    Covers=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys1){
                    postGallery1 = mediaPath;
                    String filename=postGallery1.substring(postGallery1.lastIndexOf("/")+1);
                    Gallery1.setVisibility(View.VISIBLE);
                    tvGallery1.setVisibility(View.VISIBLE);
                    Gallery1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery1.setText(filename);
                    Gallerys1=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys2){
                    postGallery2 = mediaPath;
                    String filename=postGallery2.substring(postGallery2.lastIndexOf("/")+1);
                    Gallery2.setVisibility(View.VISIBLE);
                    tvGallery2.setVisibility(View.VISIBLE);
                    Gallery2.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery2.setText(filename);
                    Gallerys2=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys3){
                    postGallery3 = mediaPath;
                    String filename=postGallery3.substring(postGallery3.lastIndexOf("/")+1);
                    Gallery3.setVisibility(View.VISIBLE);
                    tvGallery3.setVisibility(View.VISIBLE);
                    Gallery3.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery3.setText(filename);
                    Gallerys3=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }else if(Gallerys4){
                    postGallery4 = mediaPath;
                    String filename=postGallery4.substring(postGallery4.lastIndexOf("/")+1);
                    Gallery4.setVisibility(View.VISIBLE);
                    tvGallery4.setVisibility(View.VISIBLE);
                    Gallery4.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGallery4.setText(filename);
                    Gallerys4=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if(Covers){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(Cover);
                    postFoto = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(Cover);
                    postFoto = fileUri.getPath();
                }
                String filename=postFoto.substring(postFoto.lastIndexOf("/")+1);
                Cover.setVisibility(View.VISIBLE);
                tvCover.setVisibility(View.VISIBLE);
                tvCover.setText(filename);
                Covers=false;
            }else if(Gallerys1){
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