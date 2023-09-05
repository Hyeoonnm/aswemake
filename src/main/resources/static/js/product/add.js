document.getElementById("addBtn").onclick = function () {
    const name = document.querySelector("input[name='name']").value;
    const price = document.querySelector("input[name='price']").value;

    const data = {
        name: name,
        price: price
    }

    fetch("../product/api/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.ok) {
            alert("상품 등록 완료");
            window.location.href = '/product/list';
        }
    }).catch((error) => console.log(error));
};