package com.andromob.mytoursittunisia.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andromob.mytoursittunisia.OnSwipeTouchListener;
import com.andromob.mytoursittunisia.R;
import com.andromob.mytoursittunisia.db.CustomAdapter;
import com.andromob.mytoursittunisia.db.DatabaseHelper;
import com.andromob.mytoursittunisia.pojo.Contact;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentContact  extends Fragment {

    private Button btnStore, btnGetall;
    private EditText etname, ettel;
    private ListView listView;
    private ArrayList<Contact> contactArrayList;
    private CustomAdapter customAdapter;
    public static DatabaseHelper databaseHelper;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private OnSwipeTouchListener onSwipeTouchListener;



    public static FragmentContact newInstance(){return new FragmentContact();}



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_contact, container,false);


        databaseHelper = new DatabaseHelper(getContext());

        //add new contact
        btnStore = (Button) rootView.findViewById(R.id.btnstore);
       // btnGetall = (Button) rootView.findViewById(R.id.btnget);
        etname = (EditText) rootView.findViewById(R.id.etname);
        ettel = (EditText) rootView.findViewById(R.id.ettel);



        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addContactDetail(etname.getText().toString(), ettel.getText().toString());
                etname.setText("");
                ettel.setText("");
                getListContact(rootView);
                Toast.makeText(getContext(), "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //permission sms
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        //get list of contacts
        getListContact(rootView);

        listView.setOnTouchListener(new OnSwipeTouchListener(getActivity(),
                listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(GetAllContactsActivity.this,UpdateDeleteActivity.class);
                intent.putExtra("contact",contactArrayList.get(position));
                startActivity(intent);*/

                //get current position


                String sms = "test sms";

                String phoneNum = contactArrayList.get(position).getTel().toString();


                //String phoneNum ="23028502";
                if(!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                    if(checkPermission()) {

                    //Get the default SmsManager//

                        SmsManager smsManager = SmsManager.getDefault();

                    //Send the SMS//

                        smsManager.sendTextMessage(phoneNum, null, sms, null, null);
                        Toast.makeText(getContext(),"Item Action",Toast.LENGTH_SHORT).show();
                        databaseHelper.deleteContact(0);
                    }else {
                        Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        return rootView;
    }

    public void getListContact(View rootView){
        listView = (ListView) rootView.findViewById(R.id.lv);
        contactArrayList = databaseHelper.getAllContacts();

        customAdapter = new CustomAdapter(getContext(),contactArrayList);
        listView.setAdapter(customAdapter);


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getContext(),
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(),
                            "Permission denied", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    //get current position
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

}
