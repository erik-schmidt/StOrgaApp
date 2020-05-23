package com.group3.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CheckMatrNrClass {

    /**
     * checkMatriculationNumber
     * checks if the number only hold nummeric values and if the number is exactly 6 values ling
     * @param matrNr String
     * @return string matricular number
     * @throws Exception
     */
    public boolean checkMatriculationNumber(String matrNr) throws Exception{
        try{
            if (matrNr.trim().isEmpty()){
            throw new NoMatrNrException("Error: No MatrNr is given!");
        }
            int matNumberInt = Integer.parseInt(matrNr);
            if(!(matrNr.length()==6)){
                throw new Exception("not 6 long");
            }
        }catch (Exception e) {
            if (e.getClass() == NumberFormatException.class) {
                throw new NoMatrNrException("In matricular number are no letters allowed. " +
                        " Take care of the allowed length of 6 units");
            } else {
                throw new NoMatrNrException("Matriculation Number has not the right length. " +
                        "It must be exactly 6 units long. Only numbers are allowes");
            }
        }
        return true;
    }


}
