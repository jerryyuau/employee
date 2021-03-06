
var app = new Vue({
    el: '#app',
    data: {
      message: '',
	  error: '',
      entries: [
          ["1"],
          ["", "2"],
          ["", "", 3]
      ],
      columns: [],
      name: "",
      managerName: ""
    },
    created: function() {
        this.getData = this.getData.bind(this);
        this.fetchData = this.fetchData.bind(this);
        this.start();
		},
	methods: {
        start: function() {
            this.fetchData(this.getData);            
        },

		fetchData: function(getData) {
			console.log("time:" + new Date());
			
			this.$http.get("http://localhost:8181/getemployees")
				.then(function (response) {
                    console.log(response);
                    var data = response.data;
                    var result = {};
                    response.data.forEach(entry => {
                          var manager = result[entry.managerName];
                          if(!manager) {
                              manager = {
                                            name: entry.managerName,
                                            child: []
                                        };
                              result[entry.managerName] = manager;
                          }
                          var employee = result[entry.name];
                          if(!employee) { 
                                employee = {
                                    name: entry.name,
                                    child: []
                                };
                                result[entry.name] = employee;
                          }
                          manager.child.push(employee);
                    });
                    getData(result[""]);
				})
				.catch(function (error) {
					console.log(error);
            });
            setTimeout(this.start, 3000);            
		},
		getData: function(employee) {
            var result = [];
            var rowIndexs = 0;
            var columnIndex = 0;
            var stack = [];
            employee.child.forEach(elm => {
                stack.push(elm);
            })
            while(stack.length) {
                var nextStack = [];
                
                stack.forEach(element=> {
                    var data = [];
                    data[columnIndex] = element.name;
                    result[rowIndexs ++] = data;
                    element.child.forEach(child => {
                        nextStack.push(child);
                    })                    
                });
                
                stack = nextStack;
                columnIndex ++;
            }
            this.entries = result; 
            this.columns = [...Array(columnIndex).keys()];
        },

        submit: function(event) {
            if(this.name == "") {
                return;
            }
            var obj = {
                name: this.name,
                managerName: this.managerName
            };
			this.message = "";
			this.error = "";
            this.$http.post("http://localhost:8181/add", obj)
              .then(function (response) {
                console.log("post response" + response);
				this.message = "add employee " + obj.name + "with manager " + obj.managerName + " is successful";
              })
              .catch(function (error) {
                console.log("post error:" + error);
				this.error = error.body.message;
              });
        }
	}
  });
