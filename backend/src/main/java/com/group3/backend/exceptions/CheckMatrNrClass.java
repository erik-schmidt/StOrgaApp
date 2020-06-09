package com.group3.backend.exceptions;

public class CheckMatrNrClass {

    /**
     * Method to check if a matrNr is valid.
     * @param matrNr
     *                  The matrNr which should be checked.
     * @return
     *          Returns {@code true} if the matrNr has the right length and syntax. Otherwise it returns {@code false}
     *          and throws an exception.
     * @throws Exception
     *                      Thrown if the checked matrNr is not exact 6 numbers long.
     */
    public boolean checkMatriculationNumber(String matrNr) throws Exception{
        try{
            if (matrNr.trim().isEmpty()){
            throw new MatrNrException("Error: There is no MatrNr given!");
        }
            int matNumberInt = Integer.parseInt(matrNr);
            if(!(matrNr.length()==6)){
                throw new Exception("Error: The MatrNr must be exactly six numbers long!");
            }
        }catch (Exception e) {
            if (e.getClass() == NumberFormatException.class) {
                throw new MatrNrException("Error: In matrNr are no letters allowed. ");
            } else {
                throw new MatrNrException("Error: The matrNr has not the right length. " +
                        "It must be exactly 6 numbers long.");
            }
        }
        return true;
    }




}
