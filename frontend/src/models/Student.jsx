function Student(
  matrNr,
  studentPrename,
  studentFamilyname,
  fieldOfStudy,
  currentSemester,
  username,
  password
) {
  this.matrNr = matrNr;
  this.studentPrename = studentPrename;
  this.studentFamilyname = studentFamilyname;
  this.fieldOfStudy = fieldOfStudy;
  this.currentSemester = currentSemester;
  this.username = username;
  this.password = password;
}

export default Student;
