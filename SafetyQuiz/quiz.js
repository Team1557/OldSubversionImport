var pressButton = null;

$(document).ready(function() {
var correct = "answerCorrect.png"
var almostCorrect = "answerAlmostCorrect.png"
var wrong = "answerWrong.png"
var veryWrong = "answerVeryWrong.png"

var scoreCorrect = 1000;
var scoreAlmost = 750;
var scoreWrong = 100;
var scoreVeryWrong = 0;

var questions = [
	{
		question:"You are in the pit area, what shoes should you be wearing?",
		answers:["Tennis shoes", 	"Flip-flops", 		"Barefoot", 		"Boots", 		""],
		values:	[scoreCorrect,				210,				0,					750,			0],
		images:	[correct,			wrong,				veryWrong,			correct,		""],
	},
	{
		question:"You are in the pit area, what should you wear for eye protection?",
		answers:["I don't need anything", 		"Sunglasses", 		"Safety glasses", 		"", 			""],
		values:	[0,								210,				scoreCorrect,					0,				0],
		images:	[veryWrong,						almostCorrect,		correct,				"",				""],
	},
	{
		question:"You’re working on the robot, when you notice that one of the wheels are loose, what should you do? ",
		answers:["I should tell somebody", 			"I should help fix it", 		"It's fine, I don't need to let anybody know", 		"Write a memo for later", 	""],
		values:	[scoreCorrect,								scoreCorrect,							0,													150,						0],
		images:	[correct,							correct,						veryWrong,											wrong,						""],
	},
	{
		question:"There’s some liquid leaking out of the battery, what should you do?",
		answers:["Wipe it up with a paper towel", 			"Pour baking soda on it", 		"Pour baking powder on it", 		"Taste it to see if it's Kool-aid", 	""],
		values:	[40,										scoreCorrect,							0,									0,										0],
		images:	[wrong,										correct,						wrong,								veryWrong,								""],
	},
	{
		question:"Somebody is about to drill into a piece of metal, when you notice they don’t have their safety glasses on.  What do you do?",
		answers:["Make frantic hand motions", 		"Yell at them", 		"Quickly tell them to put their glasses on", 		"Laugh when they lose an eye", 		""],
		values:	[0,									150,					scoreCorrect,												0,									0],
		images:	[wrong,								almostCorrect,				correct,											veryWrong,							""],
	},
	{
		question:"You need to lift the robot, you should...",
		answers:["Use the force", 			"Use the centripetal force", 		"Get a teammate to help", 		"Prepare an intricate rope and pulley system ", 	""],
		values:	[0,							60,									scoreCorrect,							33,													0],
		images:	[veryWrong,					veryWrong,							correct,						"pulley.png",										""],
	},
	/*
	Y U be stealin my source code?
	
	Allowed to use under Creative Mode Attribution-NonCommercial 3.0 Unported
	*/
	{
		question:"Your teammates are working with the wires on your robot, when you notice that it is still on and enabled.  What do you do?",
		answers:["Just disable through the driver station", 	"Just power it down with the breaker", 		"Tell them to lick the wires to see if they're on", 		"A and B", 	""],
		values:	[300,											900,										0,															scoreCorrect,		0],
		images:	[almostCorrect,									almostCorrect,								veryWrong,													correct,										""],
	},
	{
		question:"You need to determine if a heat gun is hot.  You...",
		values:	[0,													scoreCorrect,					975,										0,								0],
		answers:["Touch the heat gun with the back of your hand", 	"Smell the heat gun", 	"Touch it to something made of wood", 		"Briefly touch the metal", 		""],
		images:	[veryWrong,											correct,				almostCorrect,								wrong,							""],
	},
	{
		question:"Your little brother is visiting the pit area",
		answers:["Ignore him", 	"Tell him the rules", 	"Let him drive the robot in the next match", 		"B and C", 	""],
		values:	[0,				scoreCorrect,					0,													0,			0],
		images:	[wrong,			correct,				veryWrong,											veryWrong,	""],
	},
	{
		question:"<b>BONUS QUESTION</b><br/>Do you even lift?",
		answers:["No", "No", "No", "No", "No"],
		values:	[1,1,1,1,1],
		images:	[correct,correct,correct,correct,correct],
	},
];

var maxScore = 0;
var minScore = 0;

for (var i = 0; i < questions.length; i++) {
	localMaxScore = -100000000000000000;
	localMinScore = 100000000000000000;
	for (var j = 0; j < questions[i].values.length; j++) {
		if (questions[i].values[j] > localMaxScore) {
			localMaxScore = questions[i].values[j];
		}
		
		if (questions[i].values[j] < localMinScore) {
			localMinScore = questions[i].values[j];
		}
	}
	
	questions[i].maxScore = localMaxScore;
	questions[i].minScore = localMinScore;
	
	correct = [];
	for (j = 0; j < questions[i].answers.length; j++ ) {
		if (questions[i].values[j] == questions[i].maxScore) {
			correct.push(questions[i].answers[j]);
		}
	}
	
	questions[i].correct = correct;
	
	maxScore += localMaxScore;
	minScore += localMinScore;
}

console.debug("max score: " + maxScore);
console.debug("min score: " + minScore);

var scores = [
	// [any score less than the previous value is >>>, Text]
	[maxScore * 0, "You have gotten the worst possible score.  I'm surprised you haven't broken the computer yet!", ""],
	[maxScore * .1, "You almost got the worst score, you have a lot to learn", ""],
	[maxScore * .2, "You must have been trying REALLY hard to get such a low score.", ""],
	[maxScore * .3, "You are a hazard to everybody around you", ""],
	[maxScore * .4, "You should take a saftey class", ""],
	[maxScore * .5, "You might consider taking a safety class.", ""],
	[maxScore * .6, "You will probably want to read the safety manual again.", ""],
	[maxScore * .7, "You should read the safety manual steadily", ""], 
	[maxScore * .75, "You could do with another safety quiz", ""],
	[maxScore * .8, "You are getting better.", ""],
	[maxScore * .85, "You're pretty good, but you still have a little more to learn", ""],
	[maxScore * .9, "Very good, you know a lot about FIRST safety!", ""],
	[maxScore * .95, "You are a safety guru!", ""],
	[maxScore * 1, "Congratulations, you got 100%!", ""],
	[9001, "Congratulations, you got 100%!  It's OVER 9000!", ""],
];

var finalScore = 0;
var questionNumber = 0;

$("#scoreContainer,#scoreText,#scoreContinue,#game,#scoreReview").css("display", "none");

var abcde = ["A","B","C","D","E"];

function prepareScoreTable() {
	//$("body").css("background-image","url('simple_dashed.png')");
	//$("body").css("color","white");
	$("#scoreTable").empty();
	$("#scoreTable").append("<tr><th></th><th style='width:33%'>Question</th><th>Your Answer</th><th>Best Answer(s)</th></tr>");
	for (i = 0; i < questions.length; i++) {
		bestAnswersString = "";
		for (j = 0; j < questions[i].correct.length; j++) {
			bestAnswersString += questions[i].correct[j];
			
			// if more correct after this..
			if (j < questions[i].correct.length-1) {
				bestAnswersString += " <b>OR</b><br/>";
			}
		}
		
		correct = questions[i].gotCorrect;
		
		$("#scoreTable").append("<tr"+(i%2==0?" class='odd'":"")+"><td><img style='width:16px;height:16px' src='"+(correct?"right.png":"wrong.png")+"'/></td><td>"+questions[i].question+"</td><td><span class='"+(correct?"rightanswer":"wronganswer") +"'>"+ questions[i].answers[questions[i].chosen] + "</span></td><td>"+bestAnswersString+"</td></tr>");
	}
}

function pressButtonFunction(buttonId) {
	if (questionNumber < questions.length) {
	if (questions[questionNumber].answers[buttonId] != "") {
	finalScore += questions[questionNumber].values[buttonId];
	
	questions[questionNumber].chosen = buttonId;
	
	questions[questionNumber].gotCorrect = (questions[questionNumber].values[questions[questionNumber].chosen] == questions[questionNumber].maxScore);
	
	console.debug(finalScore);
	
	if (questions[questionNumber].images[buttonId] != "") {
		$("#spoiler")[0].src =questions[questionNumber].images[buttonId];
	}
	else {
		$("#spoiler")[0].src = "blank.png";
	}
	
	questionNumber++;
	changeQuestion(questionNumber);
	}
	}
}

// make function public
pressButton = pressButtonFunction;

$(".answerbutton").bind("click", function() {
	buttonId = $(this).parent().parent().index();
	pressButtonFunction(buttonId);
});



$(".answerbutton").bind("hover", function() {
	buttonId = $(this).parent().parent().index();
	
	if (questions[questionNumber].images[buttonId] != "") {
		$("#spoiler")[0].src =questions[questionNumber].images[buttonId];
	}
	else {
		$("#spoiler")[0].src = "";
	}
});

$("#viewScore").bind("click", function() {
	$("#main").css("display","none");
	$("#scoreReview").css("display","inline-block");
	prepareScoreTable()
});


function changeQuestion(id) {
	if (id >= questions.length) {
		
		$("#qanda,.question").fadeOut(2000);
		
		$("#spoiler")[0].src = "blank.png";
		var hasSpecial = false;
		
		if (totalTyped.indexOf("SNAKE") !== -1) {
			window.location = "fun.html";
		}
		
		if (totalTyped.indexOf("PORTAL") !== -1) {
			window.location = "portal.html";
		}
		
		if (((finalScore >= 9001) || (finalScore == maxScore)) && !hasSpecial) {
		//if (true) {
			finalScore = 9001;
			$("#videocontainer").html('<video width="100%" height="100%" id="over9000video">  <source src="Over 9000.mp4#t=26" type="video/mp4"> </object> </video>');
			$("#videocontainer video")[0].play();
			
			console.debug("HEY 1");
			setTimeout(function(){console.debug("HEY 2");$("#videocontainer video")[0].pause();$("#videocontainer").empty()}, 55000);
		}
		else {
		}
		
		$("#scoreContainer,#scoreText,#scoreContinue").css("display", "inline-block");
		
		var scoreText = "Nice try!";
		var Theimage = "";
		for (var i = 0; i < scores.length; i++) { 
			if ((scores[i][0] <= finalScore))
			{
				scoreText = scores[i][1];
				Theimage = scores[i][2];
				console.debug(scores[i]);
			}
			else {
				break;
			}
		}
		
		$(".score").html(Math.floor(finalScore / maxScore *100) + "%" + " (" + finalScore + ")");
		
		$("#scoreText").html(scoreText);
		$("#spoiler")[0].src=Theimage;
		$("body").css("background-image", Theimage);
		
		if (finalScore <= minScore) {
			$("body").css("background-image", "feelbad.gif");
		}
		
	}
	
	question = questions[id];
	if (question != null ) {
		$(".question").html(question.question);
	
	if (question.answers[0] != "") {$("#answerA").html(question.answers[0]);$("#answerA,#buttonA").fadeIn(1000);}
	else $("#answerA,#buttonA").fadeOut(1000);
	if (question.answers[1] != "") {$("#answerB").html(question.answers[1]);$("#answerB,#buttonB").fadeIn(1000);}
	else $("#answerB,#buttonB").fadeOut(1000);
	if (question.answers[2] != "") {$("#answerC").html(question.answers[2]);$("#answerC,#buttonC").fadeIn(1000);}
	else $("#answerC,#buttonC").fadeOut(1000);
	if (question.answers[3] != "") {$("#answerD").html(question.answers[3]);$("#answerD,#buttonD").fadeIn(1000);}
	else $("#answerD,#buttonD").fadeOut(1000);
	if (question.answers[4] != "") {$("#answerE").html(question.answers[4]);$("#answerE,#buttonE").fadeIn(1000);}
	else $("#answerE,#buttonE").fadeOut(1000);
	}
	
}

changeQuestion(0);
});