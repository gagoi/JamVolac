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
    
print(renvoie("WW...............................WW\
WW...............................WW\
WW...............................WW\
WW...............O...............WW\
WW..............XY...............WW\
WW...............................WW\
WW...............................WW\
WW...........Z..........X........WW\
WW................O..............WW\
WW...............XYZ.............WW\
WW.O.....YZ...................O..WW\
WW.XYZ......................XYZ..WW\
WW.........X.....................WW\
WW...............................WW\
WW.....X...............O.........WW\
WW...........X.......788887......WW\
WW.......O...........799997...O..WW\
WW.......X...........799997...Y..WW\
WW.............X.....799997......WW\
WW...................799997......WW\
WW...................788887.....EWW\
WW1222222221444412221799997122221WW\
WW1333333331566512221799997122221WW\
WW1333333331566512221799997122221WW\
"))
