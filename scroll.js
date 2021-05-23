bwgArr=[]
scrollsArr=[]

rate = 30
bwgPrice = 0
scrollPrice = 4000000
goal = 4


for(i = 0; i < 100000; i++){
bwg = 1;
scrolls = 0;
streak = 0
	while(streak < goal){
		scrolls++;
		x1 = Math.round(Math.random()*100)
		if(x1 < rate){
			streak++;
		} else{
			bwg++;
			streak = 0
		}
	}
bwgArr.push(bwg)
scrollsArr.push(scrolls)
}

bwgSum = bwgArr.reduce(function(a, b) { return a + b; });
bwgAvg = bwgSum / bwgArr.length;

scrollSum = scrollsArr.reduce(function(a, b) { return a + b; });
scrollAvg = scrollSum / scrollsArr.length;

console.log((bwgAvg*bwgPrice+scrollPrice*scrollAvg)/1000000)

console.log(bwgAvg)
console.log(scrollAvg)

1
14.4
51
174.93827160493827160493827160494
 x=(1-p)(x+1)+p(1-p)(x+2)+p^2(1-p)(x+3)+p^3(1-p)(x+4)+p^4(1-p)(x+5)+p^5*5, p= 6/10


x = (sum from i = 1 to n p^(i-1)(1-p)(x+i)) + p^n*n





wsArr=[]
attemptsArr=[]

ws = 25
for(i=0; i < 10000; i++){
	pass = 0
	attempts = 0
	while( pass < 3 && attempts < 25){
		attempts++
		x1 = Math.round(Math.random()*100)
		if(x1 <= 10)
			pass++
		
	}
	wsArr.push(pass)
	attemptsArr.push(attempts)
}

wsSum = wsArr.reduce(function(a, b) { return a + b; });
wsAvg = wsSum / wsArr.length;

attemptsSum = attemptsArr.reduce(function(a, b) { return a + b; });
attemptsAvg = attemptsSum / attemptsArr.length;


console.log(wsAvg)
console.log(attemptsAvg)
