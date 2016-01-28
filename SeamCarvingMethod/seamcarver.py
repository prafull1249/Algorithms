__author__ = 'Prafull'

from pylab import *
from skimage import filters
import numpy as np
from matplotlib import pyplot

show_mat = False
flag_show = False


def dual_gradient_energy(img):
    R = img[:, :, 0]
    G = img[:, :, 1]
    B = img[:, :, 2]
    w, h = img.shape[:2]
    ibh = filters.sobel_h(B)
    ibv = filters.sobel_v(B)
    irh = filters.sobel_h(R)
    irv = filters.sobel_v(R)
    igh = filters.sobel_h(G)
    igv = filters.sobel_v(G)
    energy = np.zeros((w, h))
    for i in range(0, w):
        for j in range(0, h):
            energy[i][j] = (irh[i][j] * irh[i][j] + igh[i][j] * igh[i][j] +
                            ibh[i][j] * ibh[i][j] + irv[i][j] * irv[i][j] +
                            igv[i][j] * igv[i][j] + ibv[i][j] * ibv[i][j])
    gray()
    imshow(energy)
    title("Energy of image")
    show()
    for i in range(0, w):
        energy[i][0] = energy[i][1]
        energy[i][h-1] = energy[i][h-2]
    for i in range(0, h):
        energy[0][i] = energy[2][i]
        energy[w-1][i] = energy[w-2][i]

    return energy
    pass


def compute_min_seam(img, orig):
    w, h = img.shape[:2]
    min_energy = np.zeros((w, h, 2))
    for i in range(0, h):
        min_energy[w - 1][i][0] = img[w - 1][i]

    for i in range(w - 2, - 1, -1):
        for j in range(0, h):
            if (j - 1) < 0:
                l = inf
                r = min_energy[i + 1][j + 1][0]
            elif (j + 1) > h - 1:
                r = inf
                l = min_energy[i + 1][j - 1][0]
            else:
                r = min_energy[i + 1][j + 1][0]
                l = min_energy[i + 1][j - 1][0]
            m = min_energy[i + 1][j][0]
            if (m <= l and m <= r):
                min_energy[i][j][0] = m + img[i][j]
                min_energy[i][j][1] = 2
            elif ((r <= l) and (m >= r)):
                min_energy[i][j][0] = r + img[i][j]
                min_energy[i][j][1] = 3
            else:
                min_energy[i][j][0] = l + img[i][j]
                min_energy[i][j][1] = 1
    return min_energy
    pass


def find_seam(img):
    energy = dual_gradient_energy(img)
    min_energy = compute_min_seam(energy, img)
    seam = find_temp_seam(min_energy, energy, img)
    plot_seam(energy, seam)
    return seam


def find_temp_seam(min_energy, energy, img):
    min = inf
    min_index = - 1
    x = []
    y = []
    w, h = energy.shape[:]
    for i in range(0, h):
        if min > min_energy[0][i][0]:
            min = min_energy[0][i][0]
            min_index = i
    x.append(min_index)
    y.append(0)
    j = min_index
    for i in range(0, w - 1):
        if min_energy[i][j][1] == 1:
            j = j - 1
        elif min_energy[i][j][1] == 3:
            j = j + 1
        x.append(j)
        y.append(i + 1)
    return x
    pass


def plot_seam(img, seam):
    image = img.copy()
    w, h = image.shape[:2]
    flag_show = True
    imshow(img)
    title("Seam Plot on image")
    y = range(0, w)
    if flag_show is True:
        pyplot.scatter(seam, y,  color='red', s=0.4)
    if flag_show is True:
        show()
    pass


def remove_seam(img, path):
    io = 0
    jo = 0
    w, h, k = img.shape[:]
    new_img = np.zeros((w, h - 1, k))
    for i in range(0, w):
        jo = 0
        for j in range(0, h):
            if path[i] != j:
                new_img[io][jo] = img[i][j]
                jo = jo + 1
        io = io + 1
    return new_img
    pass


def transpose_img(img):
    trans_img = np.rot90(img, 1)
    np.rot90(img, 3)
    return trans_img


def width_carving(img, count):
    trans_img = img.copy()
    imshow(img)
    title("Original image for width carving")
    show()
    for i in range(0, count):
        seam = find_seam(trans_img)
        plot_seam(trans_img, seam)
        trans_img = remove_seam(trans_img, seam)
    imshow(trans_img)
    title("width carved image after carving" + str(count) + " pixels")
    show()
    return trans_img


def height_carving(img, count):
    trans_img = np.zeros((img.shape))
    trans_img = img.copy()
    trans_img = transpose_img(trans_img)
    imshow(img)
    title("Original image for height carving")
    show()
    for i in range(0, count):
        seam = find_seam(trans_img)
        plot_seam(trans_img, seam)
        trans_img = remove_seam(trans_img, seam)
        print i
    trans_img = np.rot90(trans_img, 3)
    imshow(trans_img)
    title("Final carved vertically carved image after "
          + str(count) + " pixels carved")
    show()
    return trans_img


def main():

    img = imread('test.png')
    print " hello, change the count no to decide how " \
          "many pixel to carve width and height wise"
    count = 1
    width_carving(img, count)
    height_carving(img, count)



if __name__ == '__main__':
    main()
