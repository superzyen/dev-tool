function convert() {
  var url = "http:localhost:8080/dsl/tool";
  var input = $("#input").val();
  console.log(input);
  $.ajax({
    type: "post",
    url: url,
    data: input,
    dataType: "text",
    async: true,
    success: function(response) {
      console.log("当你在使用我这个工具的时候，代表你正在变强");
      console.log("N年后你将会改变世界！！");
      console.log("success");
      $("#output").val(response);
    }
  });
}
