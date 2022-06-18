Vue.component("products", { 
	data: function () {
	    return {
	      facilities: null
	    }
	},
	    template: ` 
		<div>
			<!-- Header -->
			<header class="w3-container" style="padding-top:22px; padding-bottom: 22px;">
				<h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
			</header>
			<div style="float: right; margin-right: 1.5%;">
				<div class="search-container">
					<form action="/action_page.php">
					<input type="text" placeholder="Search.." name="search">
					<button type="submit"><i class="fa fa-search"></i></button>
					</form>
				</div>
			</div>
			
			<div style="margin-left: 1%;">
				<div class="search-container">
					<button type="submit">Dodaj</button>
				</div>
			</div>

			<div class="w3-panel">
				<div class="w3-row-padding" style="margin:0 -16px">
					<!-- <div class="w3-third">
						<h5>Regions</h5>
						<img src="/w3images/region.jpg" style="width:100%" alt="Google Regional Map">
					</div> -->
					<div>
						<h5>Feeds</h5>
						<table class="w3-table w3-striped w3-white">
							<th>
								<td>
									Naziv
								</td>
								<td>
									Tip
								</td>
								<td>
									Lokacija
								</td>
								<td>
									Prosecna ocena
								</td>
								<td>
									Radno vreme
								</td>
							</th>
							<tr v-for="(f, index) in facilities">
								<td>{{f.name}}</td>
								<td>{{f.type.name}}</td>
								<td>{{f.status}}</td>
								<td>{{f.location.adress}}</td>
							</tr>
						</tr>
						</table>
					</div>
				</div>
			</div>
			<hr>
			<hr>
			<!-- Footer -->
			<!-- <footer class="w3-container w3-padding-16 w3-light-grey">
				<h4>FOOTER</h4>
				<p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
			</footer> -->
		</div>
    	`,
    mounted () {
        axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data))
    },
    methods: {
    	addProduct : function() {
    		router.push(`/products/-1`);
    	},
    	editProduct : function(id) {
    		router.push(`/products/${id}`);
    	},
    	deleteProduct : function(id, index) {
    		r = confirm("Are you sure?")
    		if (r){
	    		axios
	            .delete('rest/products/' + id)
	            .then(response => (this.facilities.splice(index, 1)))
    		}
    	}
    }
});