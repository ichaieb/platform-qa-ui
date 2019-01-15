module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
if (vp.label === 'desktop') {

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
    casper.echo('Clicking forums button');
    casper.click('i#MenuItemtasks.uiIconFile.uiIconExt-tasks');
    casper.waitForSelector('i#MenuItemtasks.uiIconFile.uiIconExt-tasks');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}
else {
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
    casper.echo('Clicking button menu');
    casper.click('div.mobile-visible.toggle-left-bar');
    casper.wait(1000);

  });

casper.then( function(){  
    casper.echo('Clicking tasks button');
    casper.click('i#MenuItemtasks.uiIconFile.uiIconExt-tasks');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

}
