$(document).ready(function() {
  console.log("当你在使用我这个工具的时候，代表你正在变强");
  console.log("N年后你将会改变世界！！");
});

function selectTool() {
  var IsOpened = $("#tool option:selected").val();
  if (IsOpened == "dsl-tool") {
    location.href = "page/dsltool.html";
  }
}
