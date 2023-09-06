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

        const quantityInput = document.createElement("input");
        quantityInput.type = "number";
        quantityInput.min = "1";
        quantityInput.value = "1"; // 기본 개수를 1로 설정합니다.
        quantityInput.name = "count";
        quantityInput.innerHTML = "<br>";
        quantityInput.setAttribute("data-id", product.id);

        const updateDetailBtn = document.createElement("button")
        updateDetailBtn.innerHTML = "수정 내역" + "<br>";

        productDiv.appendChild(productName);
        productDiv.appendChild(productPrice);

        const adminAuth = document.getElementById("adminAuth").value;
        const userAuth = document.getElementById("userAuth").value;
        if (adminAuth === "true" && userAuth === "false") {
            const updateBtn = document.createElement("button");
            updateBtn.innerHTML = "수정" + "<br>";
            productDiv.appendChild(updateBtn);

            const deleteBtn = document.createElement("button");
            deleteBtn.innerHTML = "삭제" + "<br>";
            productDiv.appendChild(deleteBtn);
            productDiv.appendChild(updateBtn);
        }

        if (adminAuth === "false" && userAuth === "true") {
            const addBtn = document.createElement("button");
            addBtn.innerHTML = "주문에 추가하기" + "<br>";
            productDiv.appendChild(quantityInput);
            productDiv.appendChild(addBtn);
        }
        productDiv.appendChild(updateDetailBtn);
        productDiv.setAttribute("data-id", product.id);
        productList.appendChild(productDiv);
    });
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

    if (target.tagName === "BUTTON" && target.textContent === "주문에 추가하기") {
        const productDiv = target.parentElement;
        const productId = productDiv.getAttribute("data-id");
        const memberId = document.querySelector("input[name='memberId']").value;
        const count = productDiv.querySelector("input:nth-child(3)").value;


        const data = {
            productId: parseInt(productId),
            memberId: parseInt(memberId),
            count: parseInt(count)
        }

        console.log(data);

        fetch("../orderList/api/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        }).then((response) => {
            if (response.ok) {
                alert("주문 목록에 저장하였습니다.");
                window.location.href = '/product/list';
            }
        }).catch((error) => console.log(error));
    }

    if (target.tagName === "BUTTON" && target.textContent === "수정 내역") {
        const productDiv = target.parentElement;
        const productId = productDiv.getAttribute("data-id");

        function formatDate(dateString) {
            const date = new Date(dateString);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            const hours = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');
            const seconds = String(date.getSeconds()).padStart(2, '0');

            return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        }

        fetch("../product/api/prev/" + productId, {
            method: "GET",
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    alert("수정 내역이 없습니다.");
                }
            })
            .then((data) => {
                let tableContent = "수정 내역:\n\n";

                for (let i = 0; i < data.length; i++) {
                    const item = data[i];

                    const startDateFormatted = formatDate(item.startDate);
                    const endDateFormatted = formatDate(item.endDate);

                    tableContent += `# ${i + 1}\n`;
                    tableContent += `가격: ${item.price}\n`;
                    tableContent += `시작 날짜: ${startDateFormatted}\n`;
                    tableContent += `끝난 날짜: ${endDateFormatted}\n`;

                    tableContent += "\n";
                }

                alert(tableContent);
            })
            .catch((error) => console.log(error));
    }

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