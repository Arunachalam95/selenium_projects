<!DOCTYPE html>
<html>
    <link rel="shortcut icon" type="image/png" href="/AsyncInternalTools/image/icon.png" />
	<head>
		<meta charset="utf-8">
		<title>Build Signer</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="/AsyncInternalTools/css/testing.css">
	</head>

	<body>

		<div class="wrapper">
			<div class="inner">
				<form action="">
					<h3>Sign Request</h3>
					<div class="form-row">
						<div class="form-wrapper">
							<label for="">Build Excluding 2.2.</label>
							<input type="text" class="form-control" maxlength="6" placeholder="492240">
						</div>
						<div class="form-wrapper">
							<label for="">Branch Name</label>
							<input type="text" class="form-control" maxlength="7" placeholder="RC">
						</div>
					</div>
					<div class="form-row last">
						<div class="form-wrapper">
							<label for="">Environment & Version</label>
							<select name="" id="" class="form-control">
                                    <option value="All">All</option>
                                    <option value="Prod">Prod</option>
                                    <option value="Gamma">Gamma</option>
                                    <option value="Prod_Android">Prod_Android</option>
                                    <option value="Gamma_Android">Gamma_Android</option>
                                    <option value="Prod_Gamma_32">ProdGamma_32_Bit</option>
                                    <option value="Prod_Gamma_64">ProdGamma_64_Bit</option>
                                    <option value="Prod_Gamma_FireOS">ProdGamma_FireOS</option>
                                    <option value="Prod_32">Prod_32_Bit</option>
                                    <option value="Gamma_32">Gamma_32_Bit</option>
                                    <option value="Prod_64">Prod_64_Bit</option>
                                    <option value="Gamma_64">Gamma_64_Bit</option>
                                    <option value="Prod_FireOS">Prod_FireOS</option>
                                    <option value="Gamma_FireOS">Gamma_FireOS</option>
							</select>
							<i class="zmdi zmdi-chevron-down"></i>
						</div>
						<div class="form-wrapper">
							<label for="">FireOS Apk Type</label>
							<select name="" id="" class="form-control">
								<option value="release">release</option>
                                <option value="development">development</option>
							</select>
							<i class="zmdi zmdi-chevron-down"></i>
						</div>
					</div>
					<button data-text="Request">
						<span>Request</span>
					</button>
				</form>

			</div>
		</div>

		<script src="js/jquery-3.3.1.min.js"></script>

		<script src="js/main.js"></script>
	</body>
</html>