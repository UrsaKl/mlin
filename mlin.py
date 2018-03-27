from tkinter import *

class Mlin():
    def __init__(self, master):
        self.canvas = Canvas(master, width=525, height=525, background='beige')
        self.canvas.pack()
        self.polmer = 10
        self.canvas.create_rectangle(100 + self.polmer, 100 + self.polmer, 400 + self.polmer, 400 + self.polmer)
        self.canvas.create_rectangle(150 +self.polmer, 150 + self.polmer, 350 + self.polmer, 350 + self.polmer)
        self.canvas.create_line(100 + self.polmer, 100 + self.polmer, 400 + self.polmer, 400 + self.polmer)
        self.canvas.create_line(250 + self.polmer, 100 + self.polmer, 250 + self.polmer, 400 + self.polmer)
        self.canvas.create_line(100 + self.polmer, 250 + self.polmer, 400 + self.polmer, 250 + self.polmer)
        self.canvas.create_line(400 + self.polmer, 100 + self.polmer, 100 + self.polmer, 400 + self.polmer)
        self.canvas.create_rectangle(200 + self.polmer, 200 + self.polmer, 300 + self.polmer, 300 + self.polmer, fill='beige')
   
        
        self.canvas.bind("<Button-1>", self.klik)
        self.canvas.bind("<B1-Motion>", self.premik)
        self.canvas.bind("<ButtonRelease-1>", self.spusti)
        self.canvas.bind("<B3-Motion>", self.vzeti)

        
        self.koordinate = [[0,0,0],[0,3,0],[0,6,0],[1,1,0],[1,3,0],[1,5,0],[2,2,0],[2,3,0],[2,4,0],[3,0,0],[3,1,0],[3,2,0],
                           [3,4,0],[3,5,0],[3,6,0],[4,2,0],[4,3,0],[4,4,0],[5,1,0],[5,3,0],[5,5,0],[6,0,0],[6,3,0],[6,6,0]]
        for i,j,k in self.koordinate:
            x = 100 + 50*j
            y = 100 + 50*i
            self.canvas.create_oval(x, y, x + 2*self.polmer, y + 2*self.polmer, fill='white')

        self.seznamRdeci = []
        self.seznamModri = []
        for i in range(1, 10):
            self.seznamRdeci.append(self.canvas.create_oval(50, 100 + 30*i, 50 + 2*self.polmer, 100 + 30*i + 2*self.polmer, fill='red'))
            self.seznamModri.append(self.canvas.create_oval(450, 100 + 30*i, 450 + 2*self.polmer, 100 + 30*i + 2*self.polmer, fill='blue'))
        

        self.krog = None
        self.kdoJeNaPotezi = 1

        self.seznamPravilnih = {((0,0),(0,3),(0,6)),((1,1),(1,3),(1,5)), ((2,2),(2,3),(2,4)), ((3,0),(3,1),(3,2)), ((3,4),(3,5),(3,6)),
                                ((4,2),(4,3),(4,4)),((5,1),(5,3),(5,5)), ((6,0),(6,3),(6,6)), ((0,0),(3,0),(6,0)), ((1,1),(3,1),(5,1)),
                                ((2,2),(3,2),(4,2)),((0,3),(1,3),(2,3)), ((4,3),(5,3),(6,3)), ((2,4),(3,4),(4,4)), ((5,1),(3,5),(5,5)),
                                ((0,6),(3,6),(6,6)),((0,0),(1,1),(2,2)), ((6,0),(5,1),(4,2)), ((2,4),(1,5),(0,6)), ((4,4), (5,5),(6,6))}

        self.vzame = 0
        
    def klik(self, event):
        seznam = self.canvas.find_overlapping(event.x, event.y, event.x+1, event.y+1)
        if len(seznam)==0:
            return
        for krog in seznam:
            if self.kdoJeNaPotezi == 1: 
                if krog in self.seznamRdeci and event.x < 75:
                    self.x = event.x
                    self.y = event.y
                    self.krog = krog
            elif self.kdoJeNaPotezi == 2: 
                if krog in self.seznamModri and event.x > 425:
                    self.x = event.x
                    self.y = event.y
                    self.krog = krog
                

    def premik(self,event):
        if self.krog!= None:
            dx = event.x - self.x  #zdaj zapomnjen x pa prej
            dy = event.y - self.y
            self.canvas.move(self.krog, dx,dy)
            self.x = event.x
            self.y = event.y
            
        
    def spusti(self, event):
        for n in range(0, len(self.koordinate)):
            sez = set()
            # print(self.krog, 100+50*self.koordinate[n][1],100+50*self.koordinate[n][0],100+50*self.koordinate[n][1]+2*self.polmer,100+50*self.koordinate[n][0]+2*self.polmer)
            if self.kdoJeNaPotezi == 1:
                if abs(100 + 50*self.koordinate[n][1] - event.x) <= 2*self.polmer  and abs(100 + 50*self.koordinate[n][0] - event.y) <= 2*self.polmer and self.koordinate[n][2] == 0:
                    self.canvas.coords(self.krog,100+50*self.koordinate[n][1],100+50*self.koordinate[n][0],100+50*self.koordinate[n][1]+2*self.polmer,100+50*self.koordinate[n][0]+2*self.polmer)
                    self.koordinate[n][2] = 1
                    self.krog = None
                for i in self.koordinate:
                    if i[2]==1:
                        sez.add((i[0],i[1]))
                for j in self.seznamPravilnih:
                    if j in sez:
                        self.vzame = 1
                        sez = {}
                self.vzame = 0
                self.kdoJeNaPotezi = 2
                    
                    
                   
            elif self.kdoJeNaPotezi == 2:
                if abs(100 + 50*self.koordinate[n][1] - event.x) <= 2*self.polmer  and abs(100 + 50*self.koordinate[n][0] - event.y) <= 2*self.polmer and self.koordinate[n][2] == 0:
                    self.canvas.coords(self.krog,100+50*self.koordinate[n][1],100+50*self.koordinate[n][0],100+50*self.koordinate[n][1]+2*self.polmer,100+50*self.koordinate[n][0]+2*self.polmer)
                    self.koordinate[n][2] = 2
                    self.krog = None
                for i in self.koordinate:
                    if i[2]==2:
                        sez.add((i[0],i[1]))
                for j in self.seznamPravilnih:
                    if j in sez:
                        self.vzame = 2
                        sez = {}
                self.vzame = 0
                self.kdoJeNaPotezi = 1

    def vzeti(self,event):
        seznam = self.canvas.find_overlapping(event.x, event.y, event.x+1, event.y+1)
        if len(seznam)==0:
            return
        for krog in seznam:
            if self.vzame == 2: 
                if krog in self.seznamRdeci and event.x > 75:
                    self.x = event.x
                    self.y = event.y
                    self.krog = krog
            elif self.vzame == 1: 
                if krog in self.seznamModri and event.x < 425:
                    self.x = event.x
                    self.y = event.y
                    self.krog = krog
                
            
            

        





root = Tk()
aplikacija = Mlin(root)
root.mainloop()
