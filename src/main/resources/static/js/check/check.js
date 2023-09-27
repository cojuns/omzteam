let msg = "${msg}";
if (msg==="CHK_ERR") alert(`출근에 실패하였습니다.`)

let checkInBtn = document.querySelector("#checkInBtn");
const no = 111;
const isLate = 0;

checkInBtn.addEventListener("click", function () {
    const url = "/check/in"
    // fetch를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // JSON 형식으로 데이터를 전송하는 것을 명시
        },
        body: JSON.stringify({ // 객체를 JSON 문자열로 변환하여 전송
            no: no,
            isLate: isLate
        })
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // JSON 형식으로 응답 데이터를 파싱
            } else {
                throw new Error('응답이 실패하였습니다.');
            }
        })
        .then(data => {
            // 응답에서 받은 시간 데이터를 사용하여 input 태그에 값을 설정
            const checkInput  = document.querySelector("#checkIn");
            checkInput.value = data.time; // data.time에 실제 시간 데이터가 들어가야 합니다.
            console.log(checkInput.value)

            // 여기에 다른 원하는 작업을 수행할 수 있습니다.

            // 예: 알림 메시지 표시
            alert('출근되었습니다.');
            // 예: 페이지 새로고침
            // window.location.reload();
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
});

checkInBtn.addEventListener("click", function () {
    const url = "/check/in"
    // fetch를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // JSON 형식으로 데이터를 전송하는 것을 명시
        },
        body: JSON.stringify({ // 객체를 JSON 문자열로 변환하여 전송
            no: no,
            isLate: isLate
        })
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // JSON 형식으로 응답 데이터를 파싱
            } else {
                throw new Error('응답이 실패하였습니다.');
            }
        })
        .then(data => {
            // 응답에서 받은 시간 데이터를 사용하여 input 태그에 값을 설정
            const checkInput  = document.querySelector("#checkIn");
            checkInput.value = data.time; // data.time에 실제 시간 데이터가 들어가야 합니다.
            console.log(checkInput.value)

            // 여기에 다른 원하는 작업을 수행할 수 있습니다.

            // 예: 알림 메시지 표시
            alert('출근되었습니다.');
            // 예: 페이지 새로고침
            // window.location.reload();
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
});
