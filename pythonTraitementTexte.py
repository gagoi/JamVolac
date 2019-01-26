def renvoie(chainecar):
    liste=list(chainecar)
    x=0
    y=0
    i=1
    while i!=len(liste):
        if liste[i]!="\n":
            x+=1
        else:
            del (liste[i])
            y+=1
        i+=1
    return x,y,"".join(liste)
    
print(renvoie("BBBBBB................................................................BB\
BBBBBB................................................................BB\
BBBBBB................................................................BB\
BBBBBB................................................................BB\
BBBBBB...............................O................................BB\
BBBBBB..O..................DBBBBBBBBBBBBBBC...........................BB\
BBBBBB..DBBC..............................................E...........BB\
BBBBBB..................B..........................HHHHHHHHHHHHBC.....BB\
BBBBBB.......B.....................................FFFFFFFFFFFF......DBB\
BBBBBB..............B..............................GFFFFFFFFFFF......DBB\
BBBBBB..........B...................................GFFFFFFFFFFBC.....BB\
BBBBBB...............................................GFFFFFFFFF.......BB\
BBBBBB................B...............................GFFFFFFFF...DBBBBB\
BB...B......................B..........................GFFFFFFF.......BB\
BB..OB..........................B..................O....GFFFFFFB......BB\
BB...B.............................................B.....GFFFFF.......BB\
BB..B.....................................................GFFFF..B....BB\
BB..BO.......................DBBC..........................GFFF.......BB\
BBC..B......................................................GFF.....B.BB\
BB...B.......................................................GF.......BB\
BB...B................BB....RBBBBBBBBBBBBBBR..................G.B.....BB\
BBC..B................BB....SBBBBBBBBBBBBBBS..........................BB\
BB...B...............BBB....BBBBBBBBBBBBBBBR.........................DBB\
BB..DB...............BBB....BBB.......BBBBBS..........................BB\
BB...B..............BBBBO...BBB.......BBBBBBBBB.......................BB\
BB...B..............BBBBB...BBBO......BBBBBBBBB..................DBC..BB\
BBC..BBBBB.........BBBBB....BBBBBC....BBBBBBBBB.......................BB\
BB.......B.........BBBBB..............BBBBBBBBB...........DBBC........BB\
BB.......BBBBBBBBBBB...B..............BBBBBBBBB......DC...............BB\
BBBB..................OB.............DBBBBBBBBBB......................BB\
BBBBBB..............BBBB..............BBBBBBBBBB......................BB\
BBBBBBBBB.......DC...............DC........BBBBBBBC...................BB\
BBBBBBBBBBB...........................................................BB\
BBBBBBBBBBB...........................................................BB\
BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\
BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\
"))
