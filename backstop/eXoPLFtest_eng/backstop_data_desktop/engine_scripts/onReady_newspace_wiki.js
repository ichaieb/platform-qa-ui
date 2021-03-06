// onReady example
module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
//ou avec casper 2
casper.then( function(){
    casper.echo('loggin');
    if (this.exists('div.loginButton')) {
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[id="username"]':'root',
        'input[id="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.wait(8000);
    });
   }
  });

casper.then( function(){ 
    casper.echo('create space for wiki');
    casper.click('div.joinSpace a');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking button');
    casper.click('button.btn.btn-primary.pull-left');
    casper.echo('button clicked');
    casper.echo('waiting for popup');
    casper.waitForSelector('div.uiPopupMySpaces');
    casper.echo('popup showed');
    if (this.exists('div.uiPopupMySpaces')) {
        this.echo('the uiPopupMySpaces exists');
    }

  });

casper.then( function(){
  casper.waitForSelector('div.UIPopupWindow.uiPopup.UIDragObject.NormalStyle form.UIForm', function(){
      this.fillSelectors('div.UIPopupWindow.uiPopup.UIDragObject.NormalStyle form.UIForm', {
        'input[id="displayName"]':'test'
      }, true);
    casper.wait(2000);
    
      });
  });

casper.then( function(){
    casper.echo('Clicking button');
    casper.click('div.uiAction button.btn');
    casper.waitForSelector('div.uiSpaceMenu');

  });

casper.then( function(){  
    casper.echo('Clicking wiki button');
    casper.click('i.uiIconAppwiki.uiIconAppWikiPortlet.uiIconDefaultApp');
    casper.waitForSelector('div#UIViewContentDisplay.uiWikiContentDisplay');
    casper.wait(4000);
    casper.echo('newSpace wiki');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}