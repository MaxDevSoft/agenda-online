function login() {

  const nomeUsuario = $("#username").val();

  if ($.trim(nomeUsuario) == "") {
    alert("Inserir o usuário para logar!");
  } else {
    
    const urlDestino = 'LayoutPadrao.html?nomeUsuario=' + encodeURIComponent(nomeUsuario);
    
    window.location.href = urlDestino;

  }
}


