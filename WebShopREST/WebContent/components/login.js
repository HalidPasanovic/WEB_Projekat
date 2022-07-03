Vue.component("login", {
	data: function () {
		return {
			username: "",
			password: "",
			mode: "IDLE",
			facilities: null,
			temp: null,
			searchQuery: null,
			user: null
		}
	},
	template: `
		<main class="form-signin w-100 m-auto text-center">
				<img class="mb-4" src="pictures/images.jfif" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInput" placeholder="username" v-model="username">
					<label for="floatingInput">Username</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password">
					<label for="floatingPassword">Password</label>
				</div>

				<div class="checkbox mb-3">
					<label>
						<input type="checkbox" value="remember-me"> Remember me
					</label>
				</div>
				<p v-bind:hidden="mode == 'IDLE'" style="color:red">Incorrect password or username</p>
				<button class="w-100 btn btn-lg btn-dark" v-on:click = "Log">Sign in</button>
				<br><p>
				<a href="#/create">Create account</a>
				</p>
				<p class="mt-5 mb-3 text-muted">&copy; 2022–2022</p>
		</main>	  
    	`,
	mounted() {
	},
	methods: {
		Log : function(){
			if(this.username=="" || this.password=="")
			{
				this.mode = "REJECT";
			}
			else
			{
			axios
          .post('rest/login/' + this.username + '&' + this.password)
          .then(response =>
          {
			this.user = response.data;
			if(this.user.username == null)
			{
				this.mode = "REJECT";
				this.username = "";
				this.password = "";
			}
			else
			{
				if(this.user.role === "Administrator"){
					router.push('/administrator/' + this.user.username)
					//window.location.href = 'admininstrator.html';
				}
				else if(this.user.role === "Manager"){
					window.location.href = 'manager.html';
				}
				else if(this.user.role === "Trainer"){
					window.location.href = 'trainer.html';
				}
				else if(this.user.role === "Customer"){
					window.location.href = 'customer.html';
				}
			}
		  })
		}
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