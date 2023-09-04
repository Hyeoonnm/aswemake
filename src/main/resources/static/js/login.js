document.getElementById("loginBtn").onclick = function () {
    const username = document.querySelector("input[name='username']").value;
    const password = document.querySelector("input[name='password']").value;

    const data = {
        username : username,
        password : password
    }

    fetch("../member/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.ok) {
            alert("로그인 성공");
            window.location.href = '/';
        } else {
            response.text().then((text) => {
                if (text === "토큰 없음") {
                    alert("존재하지 않는 회원입니다.");
                }
            }).catch((error) => console.log(error));
        }
    }).catch((error) => console.log(error));

};