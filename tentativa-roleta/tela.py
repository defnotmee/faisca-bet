from matplotlib import pyplot as plt, animation as an
from matplotlib import lines as ln
from matplotlib import axes
import numpy as np
from itertools import count
from matplotlib.animation import FuncAnimation

arr, = plt.plot([], [], color="red")

def init():
	arr.set_data([], []);

def linhas(desl):
	arr.set_data([10 + desl, 10 + desl], [30, 60], color="red");

def animate(i, x=[], y=[]):
    plt.cla()
    x.append(i)
    y.append(random.randint(0, 10))
    plt.plot(x, y)



fig = plt.figure()
board = plt.axes(xlim=(0, 100), ylim=(0, 100))

x = 10
y = 10
r = 10
axes.Axes.set_axis_off(board)
plt.plot([0, 100], [30, 30], color="red");
plt.plot([0, 100], [60, 60], color="red");
ani = FuncAnimation(fig, linhas, init_func=init, interval=100)

	#deleta();

 #  annotation = plt.annotate("", xytext=(x, y), xy=(x+10, y+10), arrowprops=dict(facecolor='r', edgecolor='r', headwidth=6, headlength=6, width=0.1))

  # plt.draw()
   # plt.pause(0.2)
   # circle.remove()
   # annotation.remove()
   # x += 10
   # y += 10


plt.show()
