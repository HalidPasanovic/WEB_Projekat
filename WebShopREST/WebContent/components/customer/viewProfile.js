Vue.component("viewProfile", {
    data: function () {
        return {
            user: {},
            membership: {},
            usernameBefore: null
        }
    },
    template: ` 
<div class="d-flex flex-nowrap">
    <div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
        <div style="width: 500px">
            <img src="pictures/ProfilePlaceholderSuit.svg" alt="" width="100" height="100"
                class="rounded-circle me-2" style="margin-bottom: 25px; margin-left: 38%;">
            <div>
                <label for="Type" class="form-label">Username</label>
                <input type="text" class="form-control" id="Type" v-model ="user.username" placeholder="">
            </div>
            <div>
                <label for="Type" class="form-label">Password</label>
                <input type="text" class="form-control" id="Type" v-model ="user.password" placeholder="">
            </div>
            <div>
                <label for="Name" class="form-label">Name</label>
                <input type="text" class="form-control" id="Name" placeholder="" v-model ="user.name">
            </div>
            <div>
                <label for="Type" class="form-label">Surname</label>
                <input type="text" class="form-control" id="Type" v-model ="user.surname" placeholder="">
            </div>
            <div>
                <label for="Type" class="form-label">Gender</label>
                <select v-model = "user.gender" class="form-control">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div>
                <label for="Type" class="form-label">Date of Birth</label>
                <input type="date" class="form-control" id="Type" placeholder="" v-model ="user.dateOfBirth">
            </div>
            <button class="w-100 btn btn-lg btn-dark" v-on:click = "ChangeUser">Change</button>
        </div>
    </div>
</div>
    	`,
    mounted() {
        axios
            .get('rest/login/loginstat')
            .then(response => {
                this.user = response.data;
                this.usernameBefore = response.data.username
            })
    },
    methods: {
        ChangeUser: function (id) {
            try {
                axios
                    .post('rest/login/changeUser/' + this.usernameBefore, this.user)
                    .catch((e) => {alert(e?.response?.data)})
                router.push(`/`)
            } catch (error) {
                alert(error)
            }

        },
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