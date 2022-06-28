Vue.component("login", {
	data: function () {
		return {
			username: "",
			password: "",
			mode: "IDLE",
			facilities: null,
			temp: null,
			searchQuery: null
		}
	},
	template: `
		<main class="form-signin w-100 m-auto text-center">
			<form>
				<img class="mb-4" src="pictures/images.jfif" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

				<div class="form-floating">
					<input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
					<label for="floatingInput">Email address</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" placeholder="Password">
					<label for="floatingPassword">Password</label>
				</div>

				<div class="checkbox mb-3">
					<label>
						<input type="checkbox" value="remember-me"> Remember me
					</label>
				</div>
				<button class="w-100 btn btn-lg btn-dark" type="submit">Sign in</button>
				<p class="mt-5 mb-3 text-muted">&copy; 2022–2022</p>
			</form>
		</main>	  
    	`,
	mounted() {
		axios
			.get('rest/facility/')
			.then(response => (this.facilities = response.data))
	},
	methods: {
		Log: function () {
			axios
				.post('rest/login/' + this.username + '&' + this.password)
				.then(response => {
					if (response.data == 1) {
						this.temp = this.username;
						router.push('/products/')

					}
					else {
						this.mode = "REJECT";
						this.username = "";
						this.password = "";
					}
				})
		}
	},
	computed: {
		resultQuery() {
			if (this.searchQuery) {
				return this.facilities.filter((item) => {
					return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v)
						|| item.type.name.toLowerCase().includes(v)
						|| item.location.adress.street.toLowerCase().includes(v)
						|| item.location.adress.number.toString().toLowerCase().includes(v)
						|| item.location.adress.place.toLowerCase().includes(v))
				})
			} else {
				return this.facilities;
			}
		}
	}
});