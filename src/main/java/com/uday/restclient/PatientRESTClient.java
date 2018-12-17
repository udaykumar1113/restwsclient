package com.uday.restclient;

import com.uday.restclient.model.Patient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PatientRESTClient {

    private static final String PATIENT_URL = "http://localhost:8080/services/patientservice";

    public static void main(String args[]) {

        /*GET Request*/
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/services/patientservice/patients/256");
        Builder request = target.request();
        Response response = request.get();
        Patient patient = request.get(Patient.class);
        System.out.println("The response is " + response.getStatus());
        System.out.println("The patient is " + patient.toString());
        client.close();

        /*GET Request pass parameters dynamically*/
        Client client1 = ClientBuilder.newClient();
        WebTarget getTarget = client1.target(PATIENT_URL).path("/patients").path("/{id}").resolveTemplate("id",256L);
        Builder getRequest=getTarget.request();
        Response getResponse=getRequest.get();
        Patient patient1=getRequest.get(Patient.class);
        System.out.println("The response is " + getResponse.getStatus());
        System.out.println("The patient is " + patient1.toString());
        client1.close();

        /*POST Request*/
        Patient newPatient=new Patient();
        newPatient.setName("Wallace");
        Client postClient=ClientBuilder.newClient();
        WebTarget postTarget=postClient.target(PATIENT_URL).path("/patients");
        Builder postBuilder=postTarget.request();
        Patient createdPatient=postTarget.request()
                .post(Entity.entity(newPatient,MediaType.APPLICATION_XML),Patient.class);
        System.out.println("The new created patient Id is: "+createdPatient.getId());
        postClient.close();


        /*Delete Request*/
        Client deleteClient=ClientBuilder.newClient();
        WebTarget deleteTarget=deleteClient.target(PATIENT_URL).path("/patients/258");
        Builder deleteBuilder=deleteTarget.request();
        Response deleteResponse=deleteBuilder.delete();
        System.out.println("The response code is "+deleteResponse.getStatus());
        deleteClient.close();


    }
}