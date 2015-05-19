*-----------------------------------------------------------
* Title      : HW #4 Q3.3
* Written by : Yezen Rashid
* Date       : 2/7/15
* Description: Adds two numbers
*-----------------------------------------------------------
    
data1       EQU    $9A  *-87
data2       EQU    $A9  *-102  

result      EQU    $6000 *The sum of the two numbers will be stored here

start       ORG    $1000
            

*---------Code for adding-------------
        MOVE.B  #data1,D1  *puts data1 into data register D0
        MOVE.B  #data2,D2  *puts data2 into data register D1
        ADD.B   D2,D1      *adds values in data register D2 and D1
        MOVEA.W #result,A0 *moves result to address register A0
        MOVE.B  D1,(A0)    *moves the sum into A0
        
        MOVE.B  #3,D0      *print out sum
if      BVS     OVERFL     *if overflow prints message
        TRAP    #15

*---------Overflow message-------------
OVERFL  LEA     OVER,A1 
        MOVE.B  #14,D0
        TRAP    #15
        
CR      EQU     $0D         
LF      EQU     $0A

OVER    DC.B   ' Overflow occured.',CR,LF,0   
    
    END    START


*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
