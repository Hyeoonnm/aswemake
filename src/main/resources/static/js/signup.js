document.getElementById("signupBtn").onclick = function () {
    const username = document.querySelector("input[name='username']").value;
    const password = document.querySelector("input[name='password']").value;
    const role = document.querySelector("input[name='role']").value;

    const data = {
        username : username,
        password : password,
        role : role
    }

    fetch("../member/api/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.ok) {
            alert("회원가입 완료");
            window.location.href = '/';
        } else {
            response.text().then((text) => {
                if (text === "회원가입 실패") {
                    alert("이미 존재하는 이름입니다.");
                }
            }).catch((error) => console.log(error));
        }
    }).catch((error) => console.log(error));

};