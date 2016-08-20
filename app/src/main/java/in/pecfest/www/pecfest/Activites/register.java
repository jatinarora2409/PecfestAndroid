package in.pecfest.www.pecfest.Activites;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.pecfest.www.pecfest.Interfaces.CommunicationInterface;
import in.pecfest.www.pecfest.Model.Common.Constants;
import in.pecfest.www.pecfest.Model.Common.Request;
import in.pecfest.www.pecfest.Model.Common.Response;
import in.pecfest.www.pecfest.Model.Registration.RegistrationRequest;
import in.pecfest.www.pecfest.Model.Registration.RegistrationResponse;
import in.pecfest.www.pecfest.R;
import in.pecfest.www.pecfest.Utilites.Utility;

public class register extends AppCompatActivity implements CommunicationInterface {
    Editable name;
    Editable college;
    Editable email;
    Editable phone;
    Button l;
    EditText e1,e2,e3,e4;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        l= (Button) findViewById(R.id.button3);
        e1= (EditText) findViewById(R.id.input_name);
        e2= (EditText) findViewById(R.id.input_College);
        e3= (EditText) findViewById(R.id.input_Email);
        e4= (EditText) findViewById(R.id.input_phone);
       e= (EditText) findViewById(R.id.t1);

    }
    public void r(View v)
    {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Your details are: \n" + "1.Name : \t "+ e1.getText()+ "\n"+ "2.College :\t"+ e2.getText()+"\n"+"3.Email :\t"
        +e3.getText()+"\n"+"4.Phone:\t"+e4.getText()+"\n"+"Are you Sure you want to continue?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
sendrequest();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }


        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }


public void sendrequest()
{
    name=e1.getText();
    college=e2.getText();
    email=e3.getText();
    phone=e4.getText();
    String name1,college1,email1,phone1;
    name1=name.toString();
    college1=college.toString();
    email1=email.toString();
    phone1=phone.toString();

    Request rr= new Request();
    rr.method= Constants.METHOD.RESGISTRATION;
    rr.showPleaseWaitAtStart = false;
    rr.hidePleaseWaitAtEnd = false;
    rr.heading = null;
   rr.requestData= name1+email1+college1+phone1;
    Utility.SendRequestToServer(this,rr);

}

    @Override
    public void onRequestCompleted(String method, Response rr) {
        if(rr.isSuccess==false) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
        if(method.equals(Constants.METHOD.RESGISTRATION))
        {

          RegistrationResponse respone= (RegistrationResponse) Utility.getObjectFromJson(rr.JsonResponse, RegistrationResponse.class);

               Toast t=Toast.makeText(getApplicationContext(),String.valueOf(respone.response),Toast.LENGTH_SHORT);
            t.show();
        }
    }
}