package com.example.sanjana.bill;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends Activity {
    ListView listView;
    EditText editTextView;
    EditText ev;
    EditText editTextView1;
    ArrayList<Item> ItemModelList;
    CustomAdapter customAdapter;
    HashMap<String,Double> items;
    Button sub;
    private Button mButton;
    private ImageView mImageView;
    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference mStorage;
    private ProgressBar mProgress;
    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference  storageReference;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorage= FirebaseStorage.getInstance().getReference();

        listView = (ListView) findViewById(R.id.listview);
        editTextView = (EditText) findViewById(R.id.editTextView);
        editTextView1 = (EditText) findViewById(R.id.editTextView1);
        ItemModelList = new ArrayList<Item>();
        items=new HashMap<>();
        customAdapter = new CustomAdapter(this, ItemModelList);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(customAdapter);

        mButton=(Button) findViewById(R.id.upload);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);

            }
        });
        //storage = FirebaseStorage.getInstance();
        //storageReference = storage.getReference();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            mImageView = (ImageView) findViewById(R.id.receipt);
            uri=data.getData();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
/*
            uri = data.getData();
            StorageReference filepath=mStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this,"Uploading",Toast.LENGTH_LONG);
                }
            });
            }
            */
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        //String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        byte[] data = baos.toByteArray();
        StorageReference filepath=mStorage.child("Photos/"+data);
        UploadTask uploadTask = filepath.putBytes(data);

    }
  /* private void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
       storageRef = storage.getReference();
       StorageReference mountainsRef = storageRef.child("reciept.jpg");
       StorageReference mountainImagesRef = storageRef.child("images/reciept.jpg");


       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
       //String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
       byte[] data = baos.toByteArray();

       UploadTask uploadTask = mountainsRef.putBytes(data);
       uploadTask.addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception exception) {
               // Handle unsuccessful uploads
           }
       }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
              // Uri downloadUrl = taskSnapshot.getDownloadUrl();
           }
       });
   } */
  public void addValue(View v) {
      try {
          String name = editTextView.getText().toString();
          double i = Double.parseDouble(editTextView1.getText().toString());
          Item item = new Item(name, i);
          items.put(name,i);
          ItemModelList.add(item);
          customAdapter.notifyDataSetChanged();
          editTextView.setText("");
          editTextView1.setText("");
      }
      catch (NumberFormatException e)
      {
          editTextView.setText("");
          editTextView1.setText("");
          AlertDialog a = new AlertDialog.Builder(this).create();
          a.setTitle("Error");
          a.setMessage("Incorrect entry");
          a.show();
      }
  }
    public void submit(View v){


        ev = (EditText) findViewById(R.id.editText);
        if(ev.getText().toString().isEmpty())
        {
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Please enter an event name");
            a.show();
        }
        else if(ItemModelList.isEmpty())
        {
            AlertDialog a = new AlertDialog.Builder(this).create();
            a.setTitle("Error");
            a.setMessage("Please enter atleast one item");
            a.show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)ItemModelList);
            args.putSerializable("HashList",(Serializable)items);
            args.putString("NAME",ev.getText().toString());
            intent.putExtra("FOODLIST",args);
            startActivity(intent);
        }



    }
}
