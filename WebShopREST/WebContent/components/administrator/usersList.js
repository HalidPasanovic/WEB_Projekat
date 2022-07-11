Vue.component("users", { 
	data: function () {
	    return {
	      customers : null,
	      adminUsername : null,
	      admins : null,
	      managers : null,
	      trainers : null,
	      checked : false,
	      selectedType : '',
	      name: null,
	      surname: null,
	      username: null
	    }
	},
	    template: `
	    <div>
	    <div style="display: flex; justify-content: center;">
                    <h3>All users:</h3><br>
        </div>
        <div style="float: right;" class="d-flex flex-nowrap">
                    <div class="form-check" style="margin-right: 10px;">
                        <label class="form-check-label" for="same-address">Hide Deleted</label>
                        <input v-on:click="funkcija" type="checkbox" class="form-check-input" id="same-address">
                    </div>
                    <div style="width: 150px;">
                        <select v-model = "selectedType" class="form-control">
                            <option value = "" disabled selected>User type</option>
                            <option value = "All" >All</option>
                            <option value = "Manager" >Manager</option>
                            <option value = "Administrator" >Administrator</option>
                            <option value = "Trainer" >Trainer</option>
                            <option value = "Customer" >Customer</option>
                        </select>
                    </div>
                    <input style="width: 150px;" type="text" class="form-control btn-dark" v-model="name" placeholder="Name" name="search">
                    <input style="width: 150px;" type="text" class="form-control btn-dark" v-model="surname" placeholder="Surname" name="search">
                    <input style="width: 150px;" type="text" class="form-control btn-dark" v-model="username" placeholder="Username" name="search">
			    </div>
	    
	    <table class="table table-striped table-hover table-dark sortable">
	    <thead>
	    <tr>
	    <th>ID</th>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    <th>Gender</th>
	    <th>Date Of Birth</th>
	    <th>Role</th>
	    <th>Logically Deleted</th>
	    <th>Delete</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(c, index) in resultQueryC" class="item">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
					<td><button v-on:click="Delete(c.id,c.role)" class="w-100 btn btn-lg btn-dark">Delete</button></td>
		</tr>
		<tr v-for="(c, index) in resultQueryA" class="item">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
					<td><button v-on:click="Delete(c.id,c.role)" class="w-100 btn btn-lg btn-dark">Delete</button></td>
		</tr>
		<tr v-for="(c, index) in resultQueryM" class="item">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
					<td><button v-on:click="Delete(c.id,c.role)" class="w-100 btn btn-lg btn-dark">Delete</button></td>
		</tr>
		<tr v-for="(c, index) in resultQueryT" class="item">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
					<td><button v-on:click="Delete(c.id,c.role)" class="w-100 btn btn-lg btn-dark">Delete</button></td>
		</tr>
		</tbody>
		</table>
		</div>
    	`,
    mounted () {
          axios.get('rest/login/loginstat')
          .then(response => 
            {
                this.adminUsername = response.data.username
            })
			this.getAllUsers();
			
			if (localStorage.getItem('reloaded')) {
        // The page was just reloaded. Clear the value from local storage
        // so that it will reload the next time this page is visited.
        	localStorage.removeItem('reloaded');
    		} else {
        // Set a flag so that we know not to reload the page twice.
        	localStorage.setItem('reloaded', '1');
        	this.$router.go(0);
    }
    },
    
    methods: {
	
		Delete : function(id,role){
			if(role == "Administrator"){
				axios.delete('rest/admins/physically/'+ id)
				.then(alert("Deleted succesfully!"))
				.catch((e) => { alert("Exception")})
			}
			else if(role == "Manager"){
				axios.delete('rest/managers/physically/'+ id)
				.then(alert("Deleted succesfully!"))
				.catch((e) => { alert("Exception")})
			}
			else if(role == "Trainer"){
				axios.delete('rest/trainers/physically/'+ id)
				.then(alert("Deleted succesfully!"))
				.catch((e) => { alert("Exception")})
			}
			else{
				axios.delete('rest/facility/physically/'+ id)
				.then(alert("Deleted succesfully!"))
				.catch((e) => { alert("Exception")})
			}
		},
		
		funkcija : function() {
			this.checked = !this.checked
			if(this.checked)
			{
				this.customers = this.customers.filter(customer => (customer.deleted == false))
				this.managers = this.managers.filter(customer => (customer.deleted == false))
				this.admins = this.admins.filter(customer => (customer.deleted == false))
				this.trainers = this.trainers.filter(customer => (customer.deleted == false))
			}
			else
			{
				this.getAllUsers();
			}
		},
		
		getAllUsers : function() {
			axios.get('rest/customers/all')
			.then(response => (this.customers = response.data))
		axios.get('rest/admins/all')
			.then(response => {this.admins = response.data
				this.admins = this.admins.filter(admin => (admin.username !== this.adminUsername))})
    	axios.get('rest/managers/all')
			.then(response => (this.managers = response.data))
		axios.get('rest/trainers/')
			.then(response => (this.trainers = response.data))
		} 
	},
    computed: {
    resultQueryC(){
	var c = this.customers
      if(this.name){
      c = c.filter((item)=>{
        return this.name.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
      )})
      }
      if(this.surname)
      {
		c = c.filter((item)=>{
        return this.surname.toLowerCase().split(' ').every(v => item.surname.toLowerCase().includes(v) 
      )})
	  }
	  if(this.username)
      {
		c = c.filter((item)=>{
        return this.username.toLowerCase().split(' ').every(v => item.username.toLowerCase().includes(v) 
      )})
	  }
	  if(this.selectedType == "" || this.selectedType == "All" || this.selectedType == "Customer")
	  {
		return c;
	  }
	  return undefined;
      
    },
    
    resultQueryM(){
      var c = this.managers
      if(this.name){
      c = c.filter((item)=>{
        return this.name.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
      )})
      }
      if(this.surname)
      {
		c = c.filter((item)=>{
        return this.surname.toLowerCase().split(' ').every(v => item.surname.toLowerCase().includes(v) 
      )})
	  }
	  if(this.username)
      {
		c = c.filter((item)=>{
        return this.username.toLowerCase().split(' ').every(v => item.username.toLowerCase().includes(v) 
      )})
	  }
      if(this.selectedType == "" || this.selectedType == "All" || this.selectedType == "Manager")
	  {
		return c;
	  }
	  return undefined;
    },
    resultQueryA(){
      var c = this.admins
      if(this.name){
      c = c.filter((item)=>{
        return this.name.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
      )})
      }
      if(this.surname)
      {
		c = c.filter((item)=>{
        return this.surname.toLowerCase().split(' ').every(v => item.surname.toLowerCase().includes(v) 
      )})
	  }
	  if(this.username)
      {
		c = c.filter((item)=>{
        return this.username.toLowerCase().split(' ').every(v => item.username.toLowerCase().includes(v) 
      )})
	  }
      if(this.selectedType == "" || this.selectedType == "All" || this.selectedType == "Administrator")
	  {
		return c;
	  }
	  return undefined;
    },
    resultQueryT(){
      var c = this.trainers
      if(this.name){
      c = c.filter((item)=>{
        return this.name.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
      )})
      }
      if(this.surname)
      {
		c = c.filter((item)=>{
        return this.surname.toLowerCase().split(' ').every(v => item.surname.toLowerCase().includes(v) 
      )})
	  }
	  if(this.username)
      {
		c = c.filter((item)=>{
        return this.username.toLowerCase().split(' ').every(v => item.username.toLowerCase().includes(v) 
      )})
	  }
      if(this.selectedType == "" || this.selectedType == "All" || this.selectedType == "Trainer")
	  {
		return c;
	  }
	  return undefined;
    }
  }
});