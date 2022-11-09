function setCalculatesData () {
    fetch("./Result")
        .then(
            function (response) {
                response.json().then(function (data){
                    console.log(data);
                    document.getElementById("basis").innerText = data.basisAmount + " " + data.basis.shortName;
                    document.getElementById("target").innerText = data.targetAmount.toFixed(2) + " " + data.target.shortName;
                    let usdollar = 1 / data.basis.currencyKey;
                    document.getElementById("currencyKey").innerText = "1 " + data.basis.shortName + " = " + data.target.currencyKey*usdollar.toFixed(2) + " " + data.target.shortName;

                })
            }
        )
}