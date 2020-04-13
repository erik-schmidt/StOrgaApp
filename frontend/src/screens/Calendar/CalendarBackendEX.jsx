  /*
  //UseState -> 
  const[students, setStudent] = useState([]);

  // reagiert auf bestimmte Events die in der Methode passieren 
  // überwacht dauerhaft meine Methode
  // ich sage auf was er reagieren soll
   useEffect(() => {
  pingCalender().then((res)=> {
    const studentObj = [...students];
    studentObj.push(red.data);
    setStudent(studentObj);
    console.log("useEffect was called")
  });
}, []); 

// wenn die Methode verändert wurde dann 
useEffekt(() => {
  console.log("2")
}, [students]) //[] darauf reagieren  

//map -> iteration durch array 
  return (
    <View style={styles.container}> 
      {students.map(student => (
        <Text key={students.length}> {student} </Text>
      ))}
      <Text>Hier kommt ein Kalender rein! xD </Text>
    </View>
  );
}; */