import random

money = 0
slot = 5
atk = 4
cs = 120000000
ws = 150000000
wss = 0

resetB =  0

while money < 10000000000:
    if slot == 5:
        money = money + 80000000
        scroll = random.randint(0,100)
        if(scroll < 40):
            #print("fail, slot 5")
            money = money + cs
        else:
            chaos = random.randint(-5,5)
            money = money + cs + 1
            atk = atk + chaos
            slot = slot -1
            
            if atk < 0:
                atk = 0
            if(atk < 5):# reset
                atk = 4
                slot = 5
            else:                   
                print(atk, chaos)
                
            if slot == 43:
                scroll = random.randint(0,100)
                if atk > 7 :
                    money = money + ws
                if scroll < 40:
                    if atk <= 7:
                        slot = slot - 1
                    money = money + cs
                    
                else:
                    slot = slot - 1
                    chaos = random.randint(-5,5)
                    money = money + cs + 1
                    atk = atk + chaos
                    if(atk < 7):# reset
                        if resetB == 1:
                            print(atk, chaos, slot)
                        atk = 4
                        slot = 5

                    if slot == 3:
                        scroll = random.randint(0,100)
                        if atk > 9 :
                            money = money + ws
                            #print("ws", atk, slot)
                        if scroll < 40:
                            if atk <= 9:
                                slot = slot - 1
                            money = money + cs
                        else: # pass scroll
                            slot = slot - 1
                            chaos = random.randint(-5,5)
                            money = money + cs + 1
                            atk = atk + chaos
                            #print(atk, chaos, slot)
                            if(atk < 8):# reset
                                if resetB == 1:
                                    print(atk, chaos, slot)
                                atk = 4
                                slot = 5
                                
                            if slot == 2:
                                scroll = random.randint(0,100)
                                if atk > 10 :
                                    money = money + ws
                                    #print("ws", atk, slot)
                                if scroll < 40:
                                    if atk <= 10:
                                        slot = slot - 1
                                    money = money + cs
                                else:
                                    slot = slot - 1
                                    chaos = random.randint(-5,5)
                                    money = money + cs + 1
                                    atk = atk + chaos
                                    #print(atk, chaos, slot)
                                    if(atk < 8):# reset                                                   
                                        if resetB == 1:
                                            print(atk, chaos, slot)
                                        atk = 4
                                        slot = 5

                                if slot == 1:
                                    scroll = random.randint(0,100)
                                    if atk > 11 :
                                        money = money + ws
                                        #print("ws", atk, slot)
                                    if scroll < 40:
                                        if atk <= 11:
                                            slot = slot - 1
                                        money = money + cs
                                    else:
                                        slot = slot - 1
                                        chaos = random.randint(-5,5)
                                        money = money + cs + 1
                                        atk = atk + chaos
                                        print(atk, chaos, slot)
                                        
    slot = 5
    atk = 4
        
        
            
 
print(money)
