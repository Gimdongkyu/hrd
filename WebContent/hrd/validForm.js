/**
 * 이클립스 편집기 설정은 꼭 필요한 것은 아니지만 자바스크립트 소스 작성시 도움이 됩니다.
   설정 후 파일을 다시 열면 지금보이는 것과 같은 색상으로 키워드가 표시됩니다.
 */

//현재 날짜 new Date()
const today = new Date()
console.log(today)  //Thu Jul 28 2022 14:39:24 GMT+0900 (대한민국 표준시)
                    //20220728
console.log("년도 : " + today.getFullYear())                    
console.log("월 : " + (today.getMonth()+1))    //월 0~11                
console.log("일 : " + today.getDate())                    
let result = [today.getFullYear(),today.getMonth()+1,today.getDate()].join('-')      
             //배열요소를 특정기호로 연결하기
console.log(result)
const month = (today.getMonth()+1).toString().padStart(2,0)
result = [today.getFullYear(),month,today.getDate()].join('')
console.log(result)

//오늘날짜로 초기화 : 문제에서 처리조건으로 했을 때 필요합니다.
document.forms[0].regdate.value = result

function valid_check(){
    const frm = document.forms[0]
    const id = frm.seq
    const name = frm.name
    const tel = frm.tel
    const addr = frm.address
    const regdate = frm.regdate
    const grade = frm.grade
    const city = frm.city
    let isValid = true
    
    if(name.value == ''){
       alert('이름은 필수입력사항입니다.')
        name.focus()
        isValid = false
    }

/*
 유효성 검사는 출제된 문제에서 입력여부만 확인하는 처리조건일때는 
if( 검사할항목.value == '') 와 같이 간단하게만 해도 됩니다.

외부평가에서는 유효성 검사 조건이 영문자/숫자를 구분하거나 핸드폰번호,이메일 형식등에 대해
검사를 하도록 한다면 정규식이 필요합니다.
-> 간단한 정규식은 사용할 수 있도록 공부합시다.
*/

    let regex =  /^010-[0-9]{4}-[0-9]{4}$/
// 정규표현식을 검사하는 함수 regex.test(문자열) 문자열이 regex 패턴인지 검사.
//    if(regex.test(tel.value)===false) {
//        alert('입력된 값은 핸드폰 번호 형식이 아닙니다.')
    if(tel.value=='') {
        alert('핸드폰 번호는 필수 입력 항목입니다.')
        tel.focus()
    }

    if(addr.value == ''){
        alert('주소는 필수입력사항입니다.')
        addr.focus()
        isValid = false
    }
    if(regdate.value =='') { // || regdate.value != result){   //문제 처리조건에 따라 regdate.value != result 추가
       alert('가입일자는 필수 입력이고 오늘날짜로 하세요.')
        regdate.focus()
        isValid=false
    }
    regex =  /^[A-Ca-c]{1}$/    //   var valGrade = /^[ABC]$/i;
    frm.grade.value= grade.value.toUpperCase()
 if(!(grade.value == 'A' || grade.value == 'B' || grade.value == 'C')){
 //  if(regex.test(grade.value) === false){
        alert('고객등급은 A, B, C 중 하나입니다.')
        grade.focus()
        isValid=false
    }
    regex =  /^[0-9]{2}$/
//    if(regex.test(city.value)===false){
//        alert('도시코드는 숫자 2자리 입니다.')
    if(city.value =='' || city.value.length !=2 ){
        alert('도시코드는 2글자 입니다.')
        city.focus()
        isValid = false
    }
    
    if(isValid){
        //alert('유효성 통과 성공!')
      frm.submit()
    } else{
        //alert('유효성 통과 실패!')
    }
}