Vue.component("users", { 
	data: function () {
	    return {
	      customers : null,
	      adminUsername : null,
	      admins : null,
	      managers : null,
	      trainers : null,
	      checked : false
	    }
	},
	    template: `
	    <div>
	    <h1>{{this.adminUsername}}</h1><br>
	    <p>All users:</p>
	    <input type="checkbox" id="checkbox1" name="CheckBox" @change="funkcija">
	    <label for="CheckBox">Hide Deleted Users</label><br>
	    <table>
	    <tr>
	    <th>ID</th>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    <th>Gender</th>
	    <th>Date Of Birth</th>
	    <th>Role</th>
	    <th>Logically Deleted</th>
	    </tr>
	    <tr v-for="(c, index) in customers">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
		</tr>
		<tr v-for="(c, index) in admins">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
		</tr>
		<tr v-for="(c, index) in managers">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
		</tr>
		<tr v-for="(c, index) in trainers">
	    			<td>{{c.id}}</td>
					<td>{{c.name}}</td>
					<td>{{c.surname}}</td>
					<td>{{c.username}}</td>
					<td>{{c.gender}}</td>
					<td>{{c.dateOfBirth}}</td>
					<td>{{c.role}}</td>
					<td>{{c.deleted}}</td>
		</tr>
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
    },
    
    methods: {
		
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
    resultQuery(){
      if(this.searchQuery){
      return this.facilities.filter((item)=>{
        return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
        	|| item.type.name.toLowerCase().includes(v) 
        	|| item.location.adress.street.toLowerCase().includes(v) 
        	|| item.location.adress.number.toString().toLowerCase().includes(v)
        	|| item.location.adress.place.toLowerCase().includes(v))
      })
      }else{
        return this.facilities;
      }
    }
  }
});