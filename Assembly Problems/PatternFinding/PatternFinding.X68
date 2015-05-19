*-----------------------------------------------------------
* Title      : Pattern Finding and Cumulative Program
* Written by : Yezen Rashid
* Date       : 2/28/15
* Description: This program searches through the starting and
*              and ending addresses for a byte data pattern
*              and saves the address in memory. From that 
*              address it cumulatively adds the next 512 bytes
*              and stores the total sum as a word and shows
*              if there is a carry bit.
*-----------------------------------------------------------
Start_Addr      EQU     $7000 *start address
Bytes           EQU     $1001 *number of locations to check
Value           EQU     $A570 *address of pattern for comparison
Test_Pat        EQU     $FF   *Byte data pattern to compare to

Bytes_Add       EQU     $512  *number of bytes to add

    ORG    $1000
START:

****************************************************************
*Intializing data for testing by moving some random data between
*the addresses 7000 and 8000
****************************************************************
        MOVE.L  #$2342A570,$7000  *Moving random data
        MOVE.L  #$2321,$7004
        MOVE.L  #$87898,$7008
        MOVE.L  #$3213213,$700C
        MOVE.L  #$323443,$7010
        MOVE.L  #$999999,$7014    
     
        MOVE.B  #Test_Pat,Value *moved test pattern data into A570 to compare
        
****************************************************************
                *start of main program*
****************************************************************

*This takes care of most Part 2 and Part 1
        LEA     Start_Addr,A0 *moved the starting address into A0
        MOVE.W  #Bytes,D0      *set up counter to check memory loc 7000-8000 
        MOVE.B  Value,D3       *move data pattern into D3
   
Loop    MOVE.B  (A0),D2    *Move the byte data pattern from A0 into D2
        CMP.B   D3,D2       *compare data in D3 with D2
        BEQ     Found
        SUB.W   #1,D0       *Decrement counter.
        ADDA.L  #1,A0       *Increment memory pointer.
        BNE     Loop        *Loop if not 0
        
        MOVE.L  #$7000,Addr1 *if data pattern is not found, then Addr1 is 7000
        BRA     AddPart3     *branch to Part 3

****************************************************************
*Once a data pattern is found, the longword address of the data
*is moved into Addr1.
****************************************************************
Found
        MOVE.L  A0,Addr1        *stores the longword address into Addr1
        
****************************************************************
*This solves Part 3 and Part4. From Addr1 the next 512 memory locations
*are added together and placed into Addsum.  If an overflow occurs
*the carry bit is stored in Carrybit as a byte of 01.
****************************************************************
AddPart3
        CLR     D0
        CLR     D1
        
        MOVEA.L Addr1,A1        *Move the starting address into A1
        MOVE.W  #Bytes_Add,D1   *set up counter to Add next 512 bytes
        
Loop2   MOVE.B  (A1)+,D5    *Move a byte data from A1 into D5 and increment A1.
        ADD.L   D5,D0       *Add the byte data from D5 into D4 and increment    
        SUB.W   #1,D1       *Decrement counter.
        BNE     Loop2       *Loop if not 0
        
        MOVE.B  #0,Carrybit *initialize carrybit to 0
        MOVE.W  D0,Addsum   *moves the sum calculated in D4 into Addsum as a word
        
        ASR.L   #8,D0       *shift D0 right 16 bits in order to get rid of
        ASR.L   #8,D0       *the word
        
        CMP     #0,D0       *If D0 is 0 then it branches to No_Carry
        BEQ     No_Carry    *otherwise continues
        MOVE.B  #1,Carrybit *If carry bit is one then moves 1 to CarryBit
        
No_Carry
        *Display Addr1
        LEA     Address,A1 *Displays message to indicate this is Addr1
        MOVE.B  #14,D0
        TRAP    #15
        
        CLR 	D1          *Clear D1 register
        MOVE.L  Addr1,D1    *Move Addr1 Long into D1
        MOVE.B  #3,D0       *print Addr1 in decimal
        TRAP    #15
        
        *Display Addsum
        LEA     Sum,A1 *Displays message to indicate this is Addsum
        MOVE.B  #14,D0
        TRAP    #15
        
        CLR 	D1          *Clear D1 register
        MOVE.W  Addsum,D1   *Move Addsum word into D1
        MOVE.B  #3,D0       *print Addsum in decimal
        TRAP    #15
        
        *Display the carry bit
        LEA     Carry,A1 *Displays message to indicate this is the carry bit
        MOVE.B  #14,D0
        TRAP    #15
        
        CLR 	D1          *Clear D1 register
        MOVE.B  Carrybit,D1 *Move Carrybit byte into D1
        MOVE.B  #3,D0       *print Carrybit in decimal
        TRAP    #15
        
*---------------Output Commands-----------------        
CR      EQU     $0D         
LF      EQU     $0A

Address     DC.B   'This is Addr1: ',0
Sum         DC.B   CR,LF,'This is the word sum of the added numbers: ',0
Carry       DC.B   CR,LF,'This is the carry bit: ',0

Addr1       DS.L    1   *1 longword of memory is reserved for Addr1 after program code
Addsum      DS.W    1   *1 word of memory is reserved for Addsum after program code
Carrybit    DS.B    1   *1 byte of memory is reserved for Carrybit after program code

    END    START






*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
