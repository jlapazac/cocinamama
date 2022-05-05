package com.example.cocinamama.usecases.cart;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/*
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
 */
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.cart.recycler.ProductoItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CargarComprobanteFragment extends Fragment {

    private static final String TAG = "CargarComprobanteFragme";

    private Button btnCargarImg, btnAceptarImg;
    private ImageView myImageViewCargar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Image
    private String encodedImage;
    private String totalPedido;
    private ArrayList<ProductoItem> productosList = new ArrayList<>();
    private int userId;
    File file;

    public CargarComprobanteFragment() {
        // Required empty public constructor
    }

    public static CargarComprobanteFragment newInstance(String param1, String param2) {
        CargarComprobanteFragment fragment = new CargarComprobanteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cargar_comprobante, container, false);

        // get data
        totalPedido = getArguments().getString("totalPedido");
        productosList = (ArrayList<ProductoItem>) getArguments().getSerializable("key");
        userId = getArguments().getInt("userId");

        myImageViewCargar=view.findViewById(R.id.myImageViewCargar);
        btnCargarImg=view.findViewById(R.id.btnCargarImg);
        btnCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarComprobante();
            }
        });

        btnAceptarImg = view.findViewById(R.id.btnAceptarImg);
        btnAceptarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Primero cargo image y con esto obtengo la url
//                new AsyncSendPedidos(view, file).execute();

                DatosRegistroFragment datosRegistroFragment = new DatosRegistroFragment();

                Bundle bundle = new Bundle();
                bundle.putString("totalPedido", String.valueOf(totalPedido));
//                bundle.putString("encodedImage", encodedImage);
                bundle.putString("encodedImage", "encodedImage");
                bundle.putSerializable("key", productosList);
                bundle.putInt("userId", userId);

                datosRegistroFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.navHostContainer, datosRegistroFragment);
//                String backStateName = datosRegistroFragment.getClass().getName();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.commit();


            }
        });

        return view;
    }

    private void cargarComprobante() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione la app"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();

            try {
                final InputStream imageStream = getContext().getContentResolver().openInputStream(path);
                file = File.createTempFile("image", path.getLastPathSegment());

                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(imageStream.read());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Image to base64
//            try {
//                final Uri imageUri = data.getData();
////                String imagepath = getPath(imageUri).toString();
//                String imagepath = data.getData().toString();
//                file = new File(imagepath);

//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getContext().getContentResolver().query(imageUri,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imagepath = cursor.getString(columnIndex);
//                cursor.close();
//                final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
//                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//
//                encodedImage = BitMapToString(selectedImage);
//
////                Log.e(TAG, "onActivityResult: " + encodedImage );
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            myImageViewCargar.setImageURI(path);

        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

}

class AsyncSendPedidos extends AsyncTask<Void,View, Void> {

    View view;
    File file1;
    public AsyncSendPedidos(View view, File file1){
        this.view = view;
        this.file1 = file1;

    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0){
        String  ACCESS_KEY="AKIA4TIM2F3HWAW3ZV5M",
                SECRET_KEY="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",
                MY_BUCKET="s3-user-cocinamama",
                OBJECT_KEY="keyforthisuniqueobjectasdads";

        //AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        //AmazonS3 s3 = new AmazonS3Client(credentials);

//        s3.createBucket(MY_BUCKET);

//        java.security.Security.setProperty("networkaddress.cache.ttl" , "60");
//        s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
//        s3.setEndpoint("https://865999662799.signin.aws.amazon.com/console");
//
        //TransferUtility transferUtility = TransferUtility.builder().s3Client(s3).context(view.getContext()).build();
        //TransferNetworkLossHandler.getInstance(view.getContext());

        //TransferObserver observer = transferUtility.upload(MY_BUCKET,OBJECT_KEY,file1);

        /*observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                // do something
//                        progress.hide();
//                        path.setText("ID "+id+"\nState "+state.name()+"\nImage ID "+OBJECT_KEY);
                Log.e("PRUEBA", "onStateChanged: state" );
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent / bytesTotal * 100);
                Log.e("PRUEBA", "onProgressChanged: " + percentage );
                //                       progress.setProgress(percentage);
                //Display percentage transfered to user
            }

            @Override
            public void onError(int id, Exception ex) {
                // do something
                Log.e("PRUEBA",""+ex );
            }

        });
        */
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);

    }
}