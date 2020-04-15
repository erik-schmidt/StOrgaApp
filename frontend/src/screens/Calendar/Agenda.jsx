<Agenda
  
  items={{
    '2020-04-22': [],
    '2020-05-23': [{time: '9:00-10:00',name: 'appointment1',text: 'item 2 - any js object', height: 100}],
    '2020-05-24': [],
    '2020-05-25': [{name: 'info',text: 'item 3 - any js object'}, {text: 'any js object'}]
  }}
  markedDates={{
    '2020-05-23': {dots: [appointment]},
    '2020-05-25': {dots: [info]}
  }}
  markingType={'multi-dot'}

  loadItemsForMonth={(month) => {console.log('trigger items loading')}}
  onDayPress={(day)=>{console.log('day pressed',day)}}
  onDayChange={(day)=>{console.log('day changed',day)}}
  
  minDate={'2020-01-01'}
  maxDate={'2020-12-31'}
  pastScrollRange={4}
  futureScrollRange={8}

  renderItem={(item) => {
    return (
      <View style={[styles.item, {height: item.height}]}>
        <Text>{item.time}</Text><Text>{item.name}</Text><Text>{item.text}</Text>
        </View>);}}

  renderEmptyData = {() => {
    return ( 
  <View style={styles.emptyDate}>
    <Text>No Appointments saved </Text>
  </View>);}}

/> 
