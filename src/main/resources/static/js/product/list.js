document.addEventListener("DOMContentLoaded", function () {
window.onload = function () {
    fetch('../product/api/list', {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            createProductTable(data);
        })
}



    function createProductTable(data) {
        const productList = document.getElementById("productList");
        productList.innerHTML = "";

        data.forEach((product) => {
            const productDiv = document.createElement("div");
            productDiv.className = "product-item";

            const productName = document.createElement("span");
            productName.innerHTML = "상품명: " + product.name + "<br>";

            const productPrice = document.createElement("span");
            productPrice.innerHTML = "가격: " + product.price + "<br>";

            const updateBtn = document.createElement("button");
            updateBtn.innerHTML = "수정" + "<br>";

            const deleteBtn = document.createElement("button");
            deleteBtn.innerHTML = "삭제" + "<br>";

            productDiv.setAttribute("data-id", product.id);

            productDiv.appendChild(productName);
            productDiv.appendChild(productPrice);
            productDiv.appendChild(updateBtn);
            productDiv.appendChild(deleteBtn);

            productList.appendChild(productDiv);
        });
    }

    document.getElementById("backBtn").onclick = function () {
        window.location.href = "/member/info";
    }

    document.getElementById("addProductBtn").onclick = function () {
        window.location.href = "/product/add";
    }

    document.getElementById("productList").addEventListener("click", function (event) {
        const target = event.target;
        if (target.tagName === "BUTTON" && target.textContent === "수정") {
            const productDiv = target.parentElement;
            const productId = productDiv.getAttribute("data-id");
            const productName = productDiv.querySelector("span:nth-child(1)").textContent;
            const productPrice = productDiv.querySelector("span:nth-child(2)").textContent;

            const name = productName.split("상품명: ")[1];
            const price = productPrice.split("가격: ")[1];

            openModal(productId, name, price);
        }

        if (target.tagName === "BUTTON" && target.textContent === "삭제") {
            const productDiv = target.parentElement;
            const productId = productDiv.getAttribute("data-id");

            fetch(`../product/api/delete/${productId}`, {
                method: 'DELETE',
            })
                .then((response) => {
                    window.location.href = "../product/list";
                })
                .catch((error) => {
                    console.error('삭제 실패:', error);
                });
        }
    });

// 모달 열기
    function openModal(productId, productName, productPrice) {
        const modal = document.getElementById("editModal");
        const productNameInput = document.getElementById("modalName");
        const productPriceInput = document.getElementById("modalPrice");
        const saveChangesBtn = document.getElementById("saveChangesBtn");

        productNameInput.value = productName;
        productPriceInput.value = productPrice;
        saveChangesBtn.value = productId;

        modal.style.display = "block";
    }

// 모달 닫기
    document.getElementById("closeModal").addEventListener("click", function () {
        const modal = document.getElementById("editModal");
        modal.style.display = "none";
    });

    document.getElementById("saveChangesBtn").addEventListener("click", function () {
        const id = document.getElementById("saveChangesBtn").value;
        const name = document.querySelector("input[id='modalName']").value;
        const price = document.querySelector("input[id='modalPrice']").value;

        const data = {
            name: name,
            price: price
        }

        fetch("../product/api/update/" + id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        }).then((response) => {
            if (response.ok) {
                alert("상품 수정 완료");
                window.location.href = '../product/list';
            }
        }).catch((error) => console.log(error));
    });
});