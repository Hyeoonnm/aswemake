window.onload = function () {
    const memberId = document.querySelector("input[name=memberId]").value;

    const data = {
        id: memberId
    }

    fetch("../orderList/api/list", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                return Promise.reject("상품 정보가 없습니다.");
            }
        })
        .then((data) => {
            const table = createTable(data);
            table.id = "productTable";
            document.body.appendChild(table);
            createTable(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

function createTable(data) {
    const table = document.createElement("table");
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");
    const tfoot = document.createElement("tfoot"); // tfoot 요소 추가

    const headerRow = document.createElement("tr");
    const headers = ["#", "상품명", "가격", "갯수", "가격", "할인가"];
    headers.forEach((headerText) => {
        const th = document.createElement("th");
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    data.product.forEach((product, index) => {
        const row = document.createElement("tr");
        const cells = [
            index + 1,
            product.name,
            product.price,
            data.count[index],
            product.price * data.count[index] + "원"
        ];
        cells.forEach((cellText) => {
            const td = document.createElement("td");
            td.textContent = cellText;
            row.appendChild(td);
        });
        tbody.appendChild(row);
    });
    table.appendChild(tbody);

    const totalRow = document.createElement("tr");
    const totalCellLabel = document.createElement("td");
    totalCellLabel.textContent = "상품 가격";
    totalRow.appendChild(totalCellLabel);

    const totalCell = document.createElement("td");
    const totalPrice = data.product.reduce((acc, product, index) => {
        return acc + product.price * data.count[index];
    }, 0);
    totalCell.textContent = totalPrice + "원";
    totalRow.appendChild(totalCell);

    const deliveryRow = document.createElement("tr");
    const deliveryCellLabel = document.createElement("td");
    deliveryCellLabel.textContent = "배달비";
    deliveryRow.appendChild(deliveryCellLabel);

    const deliveryCell = document.createElement("td");
    const deliveryCost = data.delivery;
    deliveryCell.textContent = deliveryCost + "원";
    deliveryRow.appendChild(deliveryCell);

    const totalAmountRow = document.createElement("tr");
    const totalAmountCellLabel = document.createElement("td");
    totalAmountCellLabel.textContent = "총 금액";
    totalAmountRow.appendChild(totalAmountCellLabel);

    const totalAmountCell = document.createElement("td");
    const totalAmount = totalPrice + deliveryCost;
    totalAmountCell.textContent = totalAmount + "원";
    totalAmountRow.appendChild(totalAmountCell);

    tfoot.appendChild(totalRow);
    tfoot.appendChild(deliveryRow);
    tfoot.appendChild(totalAmountRow);

    table.appendChild(tfoot);
    return table;
}

const 전체비율 = 20;
const 전체고정 = 1000;
const 부분비율 = 10;
const 부분고정 = 400;

// HTML에서 요소를 가져옵니다.
const couponSelect = document.querySelector('select[name="coupon"]');
const couponBtn = document.getElementById('couponBtn');
const container = document.querySelector('div'); // 입력 필드를 추가할 컨테이너

// 특정 상품 비율 할인 또는 특정 상품 고정 금액 할인 옵션 선택 시 이벤트 처리
couponSelect.addEventListener('change', function() {
    const selectedOption = couponSelect.value;

    // 이전에 생성된 입력 필드가 있다면 제거
    const existingInput = container.querySelector('input[name="couponAmount"]');
    if (existingInput) {
        existingInput.remove();
    }

    // 특정 상품 비율 할인 또는 특정 상품 고정 금액 할인인 경우 입력 필드 생성
    if (selectedOption === '특정 비율' || selectedOption === '특정 고정') {
        const input = document.createElement('input');
        input.type = 'number';
        input.name = 'couponAmount';
        input.placeholder = '상품 번호';
        container.appendChild(input);
    }
});

// 쿠폰 적용 버튼 클릭 시 쿠폰 정보 확인
couponBtn.addEventListener('click', function() {
    const selectedOption = couponSelect.value;
    const couponAmountInput = container.querySelector('input[name="couponAmount"]');

    // 선택된 쿠폰 옵션과 입력된 할인 금액 확인
    console.log('선택된 쿠폰 옵션:', selectedOption);
    if (couponAmountInput) {
        console.log('입력된 상품 번호:', couponAmountInput.value);
    }
});