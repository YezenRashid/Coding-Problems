*-----------------------------------------------------------
* Title      : Decode a floating point number
* Written by : Yezen Rashid
* Date       : 3/1/15
* Description: This program deciphers an IEEE 32-bit floating
*              point hexadecimal represented as a user inputed
*              string and prints out the sign, exponent value,
*              and 0's in the mantissa.  This program assumes
*              the user has input a valid hexadecimal string.
*              Assumes the user only inputs 1-9 and A-F, no 
*              lower case or other characters.
*-----------------------------------------------------------

COMPAR_VAL          EQU       $40   *This value is between 9 and A and is used to check if the
                                    *value 
MANTISSA_BITS       EQU       $17   *number bits in mantissa in decimal for 32 bit                                    
****************************************************************
*These values are used to convert an ASCII character into hex
*You can subtract these values from a ASCII character to determine
*what the character is.
****************************************************************
CHAR_TO_DIGIT       EQU       $30   
CHAR_TO_LETTER      EQU       $37

*---------------Output Commands-----------------
CR      EQU     $0D
LF      EQU     $0A

    ORG    $400
START:

*---------Enter Message and Input-------------       
        LEA     WELCOME,A1 *Enter message is displayed
        MOVE.B  #14,D0     
        TRAP    #15        *prints message
        
        MOVE.B  #2,D0       *reads a string representing a hex from keyboard into A1
        TRAP    #15
       
        MOVE.B  #$8,D0             *set up counter to check 8 bytes of memory
        
****************************************************************
*Converts the user inputed string placed in A1 into hex.  Assumes
*the input is no greater then 8 characters.  If the input is less
*than 8 characters the loop is broken and it is assumed there are
*0's in front of the input hex. eg. input 32 would be $00000032.
****************************************************************
STRING_TO_HEX
        ASL.L   #4,D4    *Arthimetic shifts data in D4 left 4 bits 
        MOVE.B  (A1)+,D1 *Moves two Hexabits representing a character from A1 to D1 and increments pointer
        
        CMP.B   #0,D1    *If string is less then 8 characters, breaks out of loop. 
        BEQ     DONE     *Does this by checking for null terminate
        
        CMP.B   #COMPAR_VAL,D1           *Sees if the ASCII hex is less then $40. If it is, then                                     
        BLT     DIGIT_TO_HEX             *it assumes the ASCII is a digit character, else it is 
                                         *handeled a letter character
                                         
LETTER_TO_HEX
        SUB.B   #CHAR_TO_LETTER,D1      *Subtracts $37 from the character hex in D1 to determine its letter
        BRA     CALCULATE               *branch to calculate
        
DIGIT_TO_HEX        
        SUB.B   #CHAR_TO_DIGIT,D1         *Subtracts $30 from the character hex in D1 to determine digit
        
CALCULATE
        ADD.B   D1,D4       *Adds the determined Letter or digit from D1 into D4
        SUB.W   #1,D0       *Decrement counter.
        BNE     STRING_TO_HEX   *If counter is not 0, loop

DONE
        MOVE.L  D4,HEX      *Moves the fully converted long hex from D4 into HEX
        
****************************************************************
*Prints the SIGN Message and determines if the most significant
*bit is a 1 or 0 to see if it is negative or positive and prints
*the sign
****************************************************************   
SIGN_PRINT
        LEA     SIGN,A1  *prints SIGN message
        MOVE.B  #14,D0
        TRAP    #15
          
        LEA     HEX,A3      *loads HEX into A3
        MOVE.B  (A3),D5     *Moves the first byte into D5
        AND.B   #$80,D5     *AND $80 and D5 in order to determine if the first bit is 0 or 1
        CMP     #0,D5       *If the AND resulted in 0 that means first bit was 0 and 
        BEQ     POSITIVE    *it branches to POSITIVE
        
NEGATIVE        
        LEA     NEG,A1  *prints Negative sign
        MOVE.B  #14,D0
        TRAP    #15
        
        BRA     EXPONENT *branches to Exponent
        
POSITIVE
        LEA     POS,A1  *prints Positive sign
        MOVE.B  #14,D0
        TRAP    #15

EXPONENT
        LEA     EXPO,A1  *prints EXPO message
        MOVE.B  #14,D0
        TRAP    #15
        
        LEA     HEX,A3   *Loads HEX into A3
        MOVE.W  (A3),D5  *Moves the first word into D5     
        
        LSL.W   #1,D5    *Logical shifts left on the word in D5 by one bit in order to get rid of sign bit
        LSR.W   #8,D5    *Logical shifts right on the word in D5 by 8 bits in order to get first byte
        
        MOVE.B  D5,D1    *Moves the byte from D5 into D1
        MOVE.B  #3,D0    *Prints the value in D1 as decimal
        TRAP    #15

MANTISSA
        LEA     MANT,A1  *prints MANT message
        MOVE.B  #14,D0          
        TRAP    #15
        
        CLR     D0       *Clears data in D0
        CLR     D1       *Clears data in D1
        CLR     D5       *Clears data in D5
        
        MOVE.L  HEX,D0   *moves hex to D5
        ANDI.L  #$7FFFFF,D0 *ANDs number with D5 to set all bits except mantissa to 0
        
        MOVE.W  #MANTISSA_BITS,D1      *set up counter for mantissa bits
        
Loop    
        ASR.L   #1,D0       *Arithmetic shift right by 1 bit in D0
        BCS     IGNORE      *If carry is set meaning bit was 1, branch to ignore
        ADDI.B   #1,D5      *If carry is 0 meaning bit is 0, add 1 to D5
IGNORE
        SUB.W   #1,D1       *Decrement counter.
        BNE     Loop        *Loop if not 0
        
        MOVE.B  D5,D1    *Move byte representing number of 0's into D1
        MOVE.B  #3,D0    *Prints the value in D1 as decimal
        TRAP    #15

WELCOME     DC.B    'Please enter IEEE 32-bit floating point number in hexadecimal:',CR,LF,0

SIGN        DC.B    'Sign bit: ',0
NEG         DC.B    '-',CR,LF,0
POS         DC.B    '+',CR,LF,0

EXPO        DC.B    'Exponent: ',0
MANT        DC.B    CR,LF,'Mantissa: ',0

HEX         DS.L    1       *The converted input string is stored here in hex
    
    END    START        ; last line of source




*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
