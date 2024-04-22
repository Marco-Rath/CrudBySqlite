package com.example.crudsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText cajaName,cajaContact,cajaDob;
    Button btnInsert,btnUpdate,btnDelete,btnView;
    BDHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INITIALIZE THE ID

        //EDITTEXT
        cajaName=findViewById(R.id.cajaName);
        cajaContact=findViewById(R.id.cajaContact);
        cajaDob=findViewById(R.id.cajaDob);

        //BUTTON
        btnInsert=findViewById(R.id.btnInsert);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnView=findViewById(R.id.btnView);

        db=new BDHelper(this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=cajaName.getText().toString();
                String contactTXT=cajaContact.getText().toString();
                String dobTXT=cajaDob.getText().toString();

                Boolean checkinsertdata=db.insertuserdata(nameTXT,contactTXT,dobTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "No se Inserto Correctamente", Toast.LENGTH_SHORT).show();
                    
                    
                }

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=cajaName.getText().toString();
                String contactTXT=cajaContact.getText().toString();
                String dobTXT=cajaDob.getText().toString();

                Boolean checkupdatedata=db.updateuserdata(nameTXT,contactTXT,dobTXT);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Actualizacion correctamente", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "No se Actualizo Correctamente", Toast.LENGTH_SHORT).show();


                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=cajaName.getText().toString();

                Boolean checkdeletedata=db.deletedata(nameTXT);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Eliminacion correctamente", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "No se Elimino Correctamente", Toast.LENGTH_SHORT).show();


                }

            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No hay Reguistros", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                buffer.append("Nombre :"+res.getString(0)+"\n");
                buffer.append("Contacto :"+res.getString(1)+"\n");
                buffer.append("Fecha de Nacimiento :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entrada de Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });

    }
}