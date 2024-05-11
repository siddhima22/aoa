import java.util.Scanner;
public class RoundRobin {
    static void findw(int[] bst, int[] w, int quantum, String[] order) {
        int n = bst.length;
        int[] rem = new int[n];
        System.arraycopy(bst, 0, rem, 0, n);
        int t = 0;
        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (rem[i] > 0) {
                    done = false;

                    if (rem[i] > quantum) {
                        t += quantum;
                        rem[i] -= quantum;
                        order[t - 1] += "P" + (i + 1) + ", ";
                    } else {
                        t += rem[i];
                        w[i] = t - bst[i];
                        rem[i] = 0;
                        order[t - 1] += "P" + (i + 1) + ", ";
                    }   
		}
            }
            if (done)
                break;   }
    }
    static void findTurnAroundTime(int[] bst, int[] w, int[] turnAroundTime) {
        for (int i = 0; i < bst.length; i++) {
            turnAroundTime[i] = bst[i] + w[i];
        }
    }
    static void findAvgTime(int[] processes, int[] bst, int quantum) {
        int n = processes.length;
        int[] w = new int[n];
        int[] turnAroundTime = new int[n];
        String[] order = new String[1000]; 
        for (int i = 0; i < 1000; i++) {
            order[i] = "";
        }
        findw(bst, w, quantum, order);
        findTurnAroundTime(bst, w, turnAroundTime);
        int totalw = 0, totalTurnAroundTime = 0;
        for (int i = 0; i < n; i++) {
            totalw += w[i];
            totalTurnAroundTime += turnAroundTime[i];
        }
        System.out.println("Process  Burst Time  Waiting Time  Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("   " + processes[i] + "\t\t" + bst[i] + "\t\t" + w[i] + "\t\t"
                    + turnAroundTime[i]);
        }
        System.out.println("Average waiting time = " + (float) totalw / n);
        System.out.println("Average turnaround time = " + (float) totalTurnAroundTime / n);
        System.out.print("Order of processes executed: ");
        for (String slot : order) {
            if (!slot.equals("")) {
                System.out.print(slot.substring(0, slot.length() - 2)); 
                System.out.print(", ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = scanner.nextInt();
        int[] processes = new int[n];
        int[] bst = new int[n];
        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            bst[i] = scanner.nextInt();
            processes[i] = i + 1;
        }
        System.out.println("Enter the time quantum:");
        int quantum = scanner.nextInt();
        findAvgTime(processes, bst, quantum);}}


import java.util.Scanner;
class FCFSPScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int bst[] = new int[n];
        int w[] = new int[n];
        int tt[] = new int[n];
        float tott = 0.0f;
        float totw = 0.0f;
        System.out.println("Enter process id");
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
        }
        System.out.println("Enter burst time");
        for (int i = 0; i < n; i++) {
            bst[i] = sc.nextInt();
        }
        w[0] = 0;
        tt[0] = bst[0];
        for (int i = 1; i < n; i++) {
            w[i] = bst[i - 1] + w[i - 1];
            tt[i] = w[i] + bst[i];
        }
        for (int i = 0; i < n; i++) {
            tott += tt[i];
            totw += w[i];
        }
        System.out.println("Process id\tBurst time\tWaiting time\tTurnaround time");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t\t" + bst[i] + "\t\t" + w[i] + "\t\t" + tt[i]);
        }
        System.out.println("Average waiting time = " + totw / n);
        System.out.println("Average turnaround time = " + tott / n);
    }
} 


import java.util.Scanner;
public class SJFPScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes");
        int n = sc.nextInt();
        String name[] = new String[n];
        int bst[] = new int[n];
        int w[] = new int[n];
        int tt[] = new int[n];
        System.out.println("Enter the burst time for each process");
        for (int i = 0; i < n; i++) {
            bst[i] = sc.nextInt();
            name[i] = "P" + (i + 1);
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (bst[j] > bst[j + 1]) {
                    int temp = bst[j];
                    bst[j] = bst[j + 1];
                    bst[j + 1] = temp;  
                    String pname = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = pname;
                }}}
        w[0] = 0;
        for (int i = 1; i < n; i++) {
            w[i] = w[i - 1] + bst[i - 1];
        }
        for (int i = 0; i < n; i++) {
            tt[i] = w[i] + bst[i];
        }
        int totw = 0, tott = 0;
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(name[i] + "\t\t" + bst[i] + "\t\t" + w[i] + "\t\t" + tt[i]);
            totw += w[i];
            tott += tt[i];
        }
        System.out.println("Average Waiting Time = " + (float) totw / n);
        System.out.println("Average Turnaround Time = " + (float) tott / n);
    }
}

import java.util.Scanner;
public class PriorityNonpreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes");
        int n = sc.nextInt();
        String name[] = new String[n];
        int p[] = new int[n];
        int bst[] = new int[n];
        int w[] = new int[n];
        int pri[] = new int[n];
        System.out.println("Enter the burst time, priority");
        for (int i = 0; i < n; i++) {
            bst[i] = sc.nextInt();
            pri[i] = sc.nextInt();
            name[i] = "P" + (i + 1);
        }
        int temp;
        String pname;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (pri[j] > pri[j + 1]) { 
                    temp = pri[j];
                    pri[j] = pri[j + 1];
                    pri[j + 1] = temp;
                    temp = bst[j];
                    bst[j] = bst[j + 1];
                    bst[j + 1] = temp;
                    pname = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = pname;
                }}}
        int tt[] = new int[n];
        w[0] = 0;
        for (int i = 1; i < n; i++) {
            w[i] = w[i - 1] + bst[i - 1];
        }
     for (int i = 0; i < n; i++) {
            tt[i] = w[i] + bst[i];
        }
        int totw = 0, tott = 0;
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(name[i] + "\t\t" + bst[i] + "\t\t" + w[i] + "\t\t" + tt[i]);
            totw += w[i];
            tott += tt[i];
        }
       System.out.println("Average Waiting Time = " + (float) totw / n);
        System.out.println("Average Turnaround Time = " + (float) tott / n);
    }}

import java.util.*;
class FirstFit {
    static Scanner scanner = new Scanner(System.in);
    static void firstFit(int blockSize[], int m, String status[]) {
        System.out.println("\nBlock No.\tMemory Size\tStatus\t\tProcess Name");
   for (int i = 0; i < m; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + blockSize[i] + "\t\t" + status[i] + "\t\t");
            if (status[i].equals("NF") || status[i].equals("F")) {
                System.out.println("-");
            } else {
                System.out.println(status[i]);
            }
        }
    }
    public static void main(String[] args) {
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];
        String status[] = new String[m];
        System.out.println("Enter the memory size and status of each block:");
        for (int i = 0; i < m; i++) {
            System.out.print("Memory size of block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
            System.out.print("Status of block " + (i + 1) + " (F for free, NF for not free): ");
            status[i] = scanner.next();
            if (status[i].equals("NF")) {
                System.out.print("Process name occupying block " + (i + 1) + ": ");
                status[i] = scanner.next();
            }
        }
        firstFit(blockSize, m, status);
        System.out.print("Enter the process size: ");
        int processSize = scanner.nextInt();
        System.out.print("Enter the process name: ");
        String processName = scanner.next();
        boolean allocated = false;
        for (int i = 0; i < m; i++) {
            if (status[i].equals("F") && blockSize[i] >= processSize) {
                status[i] = processName;
                blockSize[i] -= processSize;
                System.out.println("\nProcess allocated to block " + (i + 1) + " successfully.");
                allocated = true;
                break;
            }
        }
        if (!allocated) {
            System.out.println("\nNo suitable block found for the process.");
        }
        firstFit(blockSize, m, status);
    }
}


import java.util.*;
class BestFit 
   {
    static Scanner scanner = new Scanner(System.in);
    static void bestFit(int blockSize[], int m, String status[]) 
     {
        System.out.println("\nBlock No.\tMemory Size\tStatus\t\tProcess Name");
        for (int i = 0; i < m; i++) 
          {
            System.out.print(" " + (i + 1) + "\t\t" + blockSize[i] + "\t\t" + status[i] + "\t\t");
            if (status[i].equals("NF") || status[i].equals("F")) 
               {
                System.out.println("-");
              } 
               else
               {
                System.out.println(status[i]);
               }
        }
    }
    public static void main(String[] args)
      {
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];
        String status[] = new String[m];
        System.out.println("Enter the memory size and status of each block:");
        for (int i = 0; i < m; i++)
           {
            System.out.print("Memory size of block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
            System.out.print("Status of block " + (i + 1) + " (F for free, NF for not free): ");
            status[i] = scanner.next();
            if (status[i].equals("NF"))
             {
                System.out.print("Process name occupying block " + (i + 1) + ": ");
                status[i] = scanner.next(); 
            }
        }
        bestFit(blockSize, m, status);
        System.out.print("Enter the process size: ");
        int processSize = scanner.nextInt();
        System.out.print("Enter the process name: ");
        String processName = scanner.next();
        int bestIdx = -1;
        for (int i = 0; i < m; i++)
            {
            if (blockSize[i] >= processSize && status[i].equals("F"))
               {
                if (bestIdx == -1 || blockSize[i] < blockSize[bestIdx])
                  {
                    bestIdx = i;
                }
            }
        }
        if (bestIdx != -1)
           {
            status[bestIdx] = processName; 
            blockSize[bestIdx] -= processSize;
            System.out.println("\nProcess allocated to block " + (bestIdx + 1) + " successfully.");
        } 
         else
          {
            System.out.println("\nNo suitable block found for the process.");
           }

        bestFit(blockSize, m, status);
    }
}


import java.util.*;

class WorstFit {
    static Scanner scanner = new Scanner(System.in);

    static void worstFit(int blockSize[], int m, String status[]) {
        System.out.println("\nBlock No.\tMemory Size\tStatus\t\tProcess Name");

        for (int i = 0; i < m; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + blockSize[i] + "\t\t" + status[i] + "\t\t");
            if (status[i].equals("NF") || status[i].equals("F")) {
                System.out.println("-");
            } else {
                System.out.println(status[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];
        String status[] = new String[m];

        System.out.println("Enter the memory size and status of each block:");
        for (int i = 0; i < m; i++) {
            System.out.print("Memory size of block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
            System.out.print("Status of block " + (i + 1) + " (F for free, NF for not free): ");
            status[i] = scanner.next();
            if (status[i].equals("NF")) {
                System.out.print("Process name occupying block " + (i + 1) + ": ");
                status[i] = scanner.next();
            }
        }

        worstFit(blockSize, m, status);

        System.out.print("Enter the process size: ");
        int processSize = scanner.nextInt();
        System.out.print("Enter the process name: ");
        String processName = scanner.next();

        int worstIdx = -1;
        for (int i = 0; i < m; i++) {
            if (status[i].equals("F")) {
                if (worstIdx == -1 || blockSize[i] > blockSize[worstIdx]) {
                    worstIdx = i;
                }
            }
        }

        if (worstIdx != -1 && blockSize[worstIdx] >= processSize) {
            status[worstIdx] = processName;
            blockSize[worstIdx] -= processSize;
            System.out.println("\nProcess allocated to block " + (worstIdx + 1) + " successfully.");
        } else {
            System.out.println("\nNo suitable block found for the process.");
        }

        worstFit(blockSize, m, status);
    }
}


import java.util.Scanner;

public class FIFO {
    static void fifo(int frames, int[] referenceString) {
        int[] pageFrames = new int[frames];
        int[] pageFaultTimes = new int[frames]; // To keep track of when the page was inserted
        int pageFaults = 0;
        int pageHits = 0;
        int nextFrameIndex = 0; // Pointer to the next available frame index

        System.out.print("| Reference |");
        for (int i = 1; i <= frames; i++)
            System.out.print(" Frame " + i + "|");

        System.out.println(" Page Faults |");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < referenceString.length; i++) {
            boolean found = false;

            // Check if the page is already in the frame
            for (int j = 0; j < frames; j++) {
                if (pageFrames[j] == referenceString[i]) {
                    found = true;
                    pageHits++;
                    break;
                }
            }

            if (!found) {
                pageFaults++;
                if (nextFrameIndex < frames) {
                     pageFrames[nextFrameIndex] = referenceString[i];
                    pageFaultTimes[nextFrameIndex] = i; // Set insertion time
                    nextFrameIndex++;
                } else {
                    
                    int oldestIndex = 0;
                    for (int j = 1; j < frames; j++) {
                        if (pageFaultTimes[j] < pageFaultTimes[oldestIndex]) {
                            oldestIndex = j;
                        }
                    }
                    // Replace the oldest page with the new one
                    pageFrames[oldestIndex] = referenceString[i];
                    pageFaultTimes[oldestIndex] = i; // Update insertion time
                }
            }

            // Print the current state of frames
            System.out.print("|     " + referenceString[i] + "     ");
            for (int j = 0; j < frames; j++) {
                if (j < nextFrameIndex)
                    System.out.print("|    " + pageFrames[j] + "    ");
                else
                    System.out.print("|    -    ");
            }
            System.out.println("|      " + pageFaults + "      |");
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        System.out.println("Hit Ratio: " + pageHits + "/" + referenceString.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int frames = scanner.nextInt();

        System.out.print("Enter number of reference string elements: ");
        int n = scanner.nextInt();

        int[] referenceString = new int[n];
        System.out.print("Enter reference string elements: ");
        for (int i = 0; i < n; i++)
            referenceString[i] = scanner.nextInt();

        fifo(frames, referenceString);
    }
}


import java.util.Scanner;
import java.util.Arrays;

public class LRU {
    static void lru(int frames, int[] referenceString) {
        int[] pageFrames = new int[frames];
        int[] pageTimes = new int[frames];
        Arrays.fill(pageFrames, -1);
        Arrays.fill(pageTimes, -1);
        int pageFaults = 0;
    
        int lruIdx, found, pageHits;

        System.out.print("| Reference |"); 
        for (int i = 1; i <= frames; i++)
            System.out.print(" Frame " + i + "|");
        
        System.out.println(" Page Faults |");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < referenceString.length; i++) {
            found = 0;

            for (int j = 0; j < frames; j++) {
                if (pageFrames[j] == referenceString[i]) {
                    found = 1;
                    pageTimes[j] = i;                    
                    break;
                }
            }

            if (found == 0) {
                pageFaults++;
                lruIdx = 0;

                for (int j = 1; j < frames; j++) {
                    if (pageTimes[j] < pageTimes[lruIdx])
                        lruIdx = j;
                }
                pageFrames[lruIdx] = referenceString[i];
                pageTimes[lruIdx] = i;
            }

          

            System.out.print("|     " + referenceString[i] + "     ");
            for (int j = 0; j < frames; j++) {
                if (pageFrames[j] != -1)
                    System.out.print("|    " + pageFrames[j] + "    ");
                else
                    System.out.print("|    -    ");
            }
            System.out.println("|      " + pageFaults + "      |");
        }
        pageHits = referenceString.length - pageFaults;
        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        System.out.println("Hit Ratio: " + pageHits + "/" + referenceString.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int frames = scanner.nextInt();

        System.out.print("Enter number of reference string elements: ");
        int n = scanner.nextInt();

        int[] referenceString = new int[n];
        System.out.print("Enter reference string elements: ");
        for (int i = 0; i < n; i++)
            referenceString[i] = scanner.nextInt();

        lru(frames, referenceString);
    }
}


import java.util.Scanner;

public class Optimal {
    static void optimal(int frames, int[] referenceString) {
        int[] pageFrames = new int[frames];
        int pageFaults = 0;
        int pageHits = 0;
        int nextFrameIndex = 0; 
        System.out.print("| Reference |");
        for (int i = 1; i <= frames; i++)
            System.out.print(" Frame " + i + "|");

        System.out.println(" Page Faults |");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < referenceString.length; i++) {
            boolean found = false;

            // Check if the page is already in the frame
            for (int j = 0; j < frames; j++) {
                if (pageFrames[j] == referenceString[i]) {
                    found = true;
                    pageHits++;
                    break;
                }
            }

            if (!found) {
                pageFaults++;
                if (nextFrameIndex < frames) {
                    // Insert the page into an empty frame
                    pageFrames[nextFrameIndex] = referenceString[i];
                    nextFrameIndex++;
                } else {
                    // Find the page that will not be used furthest in the future
                    int furthestIndex = -1;
                    int furthestDistance = 0;
                    for (int j = 0; j < frames; j++) {
                        int distance = futureDistance(referenceString, i, pageFrames[j]);
                        if (distance == -1) {
                            furthestIndex = j;
                            break;
                        } else if (distance > furthestDistance) {
                            furthestIndex = j;
                            furthestDistance = distance;
                        }
                    }
                    // Replace the page with the furthest distance
                    pageFrames[furthestIndex] = referenceString[i];
                }
            }

            // Print the current state of frames
            System.out.print("|     " + referenceString[i] + "     ");
            for (int j = 0; j < frames; j++) {
                if (j < nextFrameIndex)
                    System.out.print("|    " + pageFrames[j] + "    ");
                else
                    System.out.print("|    -    ");
            }
            System.out.println("|      " + pageFaults + "      |");
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        System.out.println("Hit Ratio: " + pageHits + "/" + referenceString.length);
    }

    // Function to find the distance to the next occurrence of a page in the reference string
    static int futureDistance(int[] referenceString, int currentIndex, int page) {
        for (int i = currentIndex + 1; i < referenceString.length; i++) {
            if (referenceString[i] == page) {
                return i - currentIndex;
            }
        }
        return -1; // If the page is not found in the future
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int frames = scanner.nextInt();

        System.out.print("Enter number of reference string elements: ");
        int n = scanner.nextInt();

        int[] referenceString = new int[n];
        System.out.print("Enter reference string elements: ");
        for (int i = 0; i < n; i++)
            referenceString[i] = scanner.nextInt();

        optimal(frames, referenceString);
    }
}


import java.util.Scanner;
public class MVT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalMemory, remainingMemory, numProcesses = 0;
        int[] processSizes = new int[10];
        char choice;

        System.out.print("Enter the total memory (in Bytes): ");
        totalMemory = scanner.nextInt();
        remainingMemory = totalMemory;

        do {
            System.out.print("Enter the process size of process " + (numProcesses + 1) + " (in Bytes): ");
            processSizes[numProcesses] = scanner.nextInt();

            if (processSizes[numProcesses] <= remainingMemory) {
                System.out.println("Process added successfully.");
                remainingMemory -= processSizes[numProcesses];
                numProcesses++;
            } else {
                System.out.println("Memory is full. Cannot allocate more memory.");
                break;
            }

            System.out.print("Enter 'y' if you want to add more processes: ");
            choice = scanner.next().charAt(0); // Reading the first character of the input

        } while (choice == 'y' || choice == 'Y');

        System.out.println("\nTotal memory space is " + totalMemory);
        System.out.println("\nProcess\t\tMemory Allocated");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println((i + 1) + "\t\t" + processSizes[i]);
        }
        System.out.println("\nMemory space allocated is " + (totalMemory - remainingMemory));
        System.out.println("External fragmentation: " + remainingMemory);
    }
}


import java.util.Scanner;
public class MFT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ms, bs, nob, ef, n,i, tif = 0;
        int[] mp;
        int p = 0;
        System.out.print("Enter the total memory available (in Bytes) -- ");
        ms = scanner.nextInt();
        System.out.print("Enter the block size (in Bytes) -- ");
        bs = scanner.nextInt();
        nob = ms / bs;
        ef = ms - nob * bs;
        System.out.print("\nEnter the number of processes -- ");
        n = scanner.nextInt();
        mp = new int[n];
        
        for ( i = 0; i < n; i++) {
            System.out.print("Enter memory required for process " + (i + 1) + " (in Bytes)-- ");
            mp[i] = scanner.nextInt();
        }
        
        System.out.println("\nNo. of Blocks available in memory -- " + nob);
        System.out.println("\nPROCESS\tMEMORY REQUIRED\t ALLOCATED\tINTERNAL FRAGMENTATION");
        
        for ( i = 0; i < n && p < nob; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + mp[i]);
            if (mp[i] > bs)
                System.out.println("\t\tNO\t\t---");
            else {
                System.out.println("\t\tYES\t" + (bs - mp[i]));
                tif += bs - mp[i];
                p++;
            }
        }
        
        if (i < n)
            System.out.println("\nMemory is Full, Remaining Processes cannot be accommodated");
        
        System.out.println("\nTotal Internal Fragmentation is " + tif);
        System.out.println("Total External Fragmentation is " + ef);
    }
}


import java.util.Scanner;

public class FCFSDisk {

    public static void fcfsDiskSchedule(int[] tracks, int head) {
        int totalSeekTime = 0;
        int[] seekSequence = new int[tracks.length + 1];
        seekSequence[0] = head;

        for (int i = 0; i < tracks.length; i++) {
            totalSeekTime += Math.abs(tracks[i] - head);
            head = tracks[i];
            seekSequence[i + 1] = head;
        }

        System.out.println("Seek Sequence:");
        for (int i = 0; i < seekSequence.length; i++) {
            System.out.print(seekSequence[i] + " ");
        }
        System.out.println("\nTotal seek time: " + totalSeekTime);

        float avgSeekTime = (float) totalSeekTime / tracks.length;
        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tracks: ");
        int n = scanner.nextInt();
        int[] tracks = new int[n];

        System.out.println("Enter the track positions:");
        for (int i = 0; i < n; i++) {
            tracks[i] = scanner.nextInt();
        }

        System.out.print("Enter the current head position: ");
        int head = scanner.nextInt();

        fcfsDiskSchedule(tracks, head);
    }
}


import java.util.Scanner;

public class SSTF1 {

    public static void calculateDifference(int[] queue, int head, int[] diff) {
        for (int i = 0; i < diff.length; i++) {
            diff[i] = Math.abs(queue[i] - head);
        }
    }

    public static int findMin(int[] diff, boolean[] accessed) {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!accessed[i] && minimum > diff[i]) {
                minimum = diff[i];
                index = i;
            }
        }
        return index;
    }

    public static void shortestSeekTimeFirst(int[] request, int head) {
        if (request.length == 0) return;

        int[] diff = new int[request.length];
        boolean[] accessed = new boolean[request.length];

        int seekCount = 0;
        int[] seekSequence = new int[request.length + 1];
        for (int i = 0; i < request.length; i++) {
            seekSequence[i] = head;
            calculateDifference(request, head, diff);
            int index = findMin(diff, accessed);
            accessed[index] = true;
            seekCount += diff[index];
            head = request[index];
        }
        seekSequence[seekSequence.length - 1] = head;

        System.out.println("Total number of seek operations = " + seekCount);
        System.out.println("Seek Sequence is:");
        for (int i = 0; i < seekSequence.length; i++) {
            System.out.println(seekSequence[i]);
        }
          System.out.println("Total seek time: \n"+ seekCount);
        float avgSeekTime = (float) seekCount / request.length;

        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tracks: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the requests:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        shortestSeekTimeFirst(arr, head);
    }
}

import java.util.Scanner;

public class Scan {

    static void SCAN(int[] arr, int head, String direction, int diskSize) {
        int seek_count = 0;
        int distance, cur_track;
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int leftSize = 0, rightSize = 0;

        if (direction.equals("left")) {
            left[leftSize++] = 0;
        } else if (direction.equals("right")) {
            right[rightSize++] = diskSize - 1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < head) {
                left[leftSize++] = arr[i];
            }
            if (arr[i] > head) {
                right[rightSize++] = arr[i];
            }
        }

        int[] seek_sequence = new int[arr.length + 1];

        java.util.Arrays.sort(left, 0, leftSize);
        java.util.Arrays.sort(right, 0, rightSize);

        int run = 2;
      
            if (direction.equals("left")) {
                for (int i = leftSize - 1; i >= 0; i--) {
                    cur_track = left[i];
                    seek_sequence[leftSize - i - 1] = cur_track;
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
              
                for (int i = 0; i < rightSize; i++) {
                    cur_track = right[i];
                    seek_sequence[leftSize + i] = cur_track;
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
            
            }
        
        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Seek Sequence is:");
        for (int i = 0; i < seek_sequence.length; i++) {
            System.out.println(seek_sequence[i]);
        }

        float avgSeekTime = (float) seek_count / arr.length;
        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = sc.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the requests:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = sc.nextInt();

        System.out.print("Enter the direction (left/right): ");
        String direction = sc.next();

        System.out.print("Enter the disk size: ");
        int diskSize = sc.nextInt();

        SCAN(arr, head, direction, diskSize);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class CSCAN {

    static void cscanDiskSchedule(int[] requests, int head, int diskSize) {
        int seekCount = 0;
        int distance, curTrack;

        // Include 0, head, and disk size in the array and sort it
        int[] sortedRequests = new int[requests.length + 2];
        sortedRequests[0] = 0; // Include track 0
        sortedRequests[1] = head; // Include head
        System.arraycopy(requests, 0, sortedRequests, 2, requests.length);
        Arrays.sort(sortedRequests);

        // Find the index of head in sorted requests
        int index = Arrays.binarySearch(sortedRequests, head);

        // If head is not present in the requests array, adjust index
        if (index < 0) {
            index = -(index + 1);
        }

        System.out.println("Seek Sequence is:");

        // Handle requests before the head position
        for (int i = index - 1; i >= 0; i--) {
            curTrack = sortedRequests[i];
            distance = Math.abs(curTrack - head);
            seekCount += distance;
            head = curTrack;
            System.out.println(head);
        }

        // Move the head to the end of the disk if not already there
        if (head != diskSize - 1) {
            distance = Math.abs(diskSize - 1 - head);
            seekCount += distance;
            head = diskSize - 1;
            System.out.println(head);
        }

        // Handle requests after the head position
           for (int i = sortedRequests.length - 1; i > index; i--) {
            curTrack = sortedRequests[i];
            distance = Math.abs(curTrack - head);
            seekCount += distance;
            head = curTrack;
            System.out.println(head);
        }

        System.out.println("Total number of seek operations = " + seekCount);
        float avgSeekTime = (float) seekCount / requests.length;
        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the requests:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        System.out.print("Enter the disk size: ");
        int diskSize = scanner.nextInt();

        cscanDiskSchedule(arr, head, diskSize);
    }
}

import java.util.Scanner;
import java.util.Arrays;

public class LOOK {

    static void lookDiskSchedule(int[] requests, int head, int diskSize, String direction) {
        int seekCount = 0;
        int distance, curTrack;
        int[] sortedRequests = Arrays.copyOf(requests, requests.length);
        Arrays.sort(sortedRequests);

        // Find the index of head in sorted requests
        int index = Arrays.binarySearch(sortedRequests, head);

        // If head is not present in the requests array, adjust index
        if (index < 0) {
            index = -(index + 1);
        }

        System.out.println("Seek Sequence is:");

        if (direction.equals("left")) {
            // Forward scan from head to the end of the requests
            for (int i = index; i < sortedRequests.length; i++) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }

            // Backward scan from head to the beginning of the requests
            for (int i = index - 1; i >= 0; i--) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }
        } else if (direction.equals("right")) {
            // Backward scan from head to the beginning of the requests
            for (int i = index - 1; i >= 0; i--) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }

            // Forward scan from head to the end of the requests
            for (int i = index; i < sortedRequests.length; i++) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }
        }

        System.out.println("Total number of seek operations = " + seekCount);
        float avgSeekTime = (float) seekCount / requests.length;
        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the requests:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        System.out.print("Enter the direction (left/right): ");
        String direction = scanner.next();

        System.out.print("Enter the disk size: ");
        int diskSize = scanner.nextInt();

        lookDiskSchedule(arr, head, diskSize, direction);
    }
}

import java.util.Scanner;
import java.util.Arrays;

public class CLOOK {

    static void clookDiskSchedule(int[] requests, int head, int diskSize, String direction) {
        int seekCount = 0;
        int distance, curTrack;
        int[] sortedRequests = Arrays.copyOf(requests, requests.length);
        Arrays.sort(sortedRequests);

        // Find the index of head in sorted requests
        int index = Arrays.binarySearch(sortedRequests, head);

        // If head is not present in the requests array, adjust index
        if (index < 0) {
            index = -(index + 1);
        }

        System.out.println("Seek Sequence is:");

        if (direction.equals("left")) {
            // Forward scan from head to the end of the requests
            for (int i = index; i < sortedRequests.length; i++) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }

            // Wrap around and scan from the beginning of the requests to the head
            for (int i = 0; i < index; i++) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }
        } else if (direction.equals("right")) {
            // Forward scan from head to the beginning of the requests
            for (int i = index - 1; i >= 0; i--) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }

            // Wrap around and scan from the end of the requests to the head
            for (int i = sortedRequests.length - 1; i >= index; i--) {
                curTrack = sortedRequests[i];
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
                System.out.println(head);
            }
        }

        System.out.println("Total number of seek operations = " + seekCount);
        float avgSeekTime = (float) seekCount / requests.length;
        System.out.printf("Average seek time: %.2f\n", avgSeekTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the requests:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        System.out.print("Enter the direction (left/right): ");
        String direction = scanner.next();

        System.out.print("Enter the disk size: ");
        int diskSize = scanner.nextInt();

        clookDiskSchedule(arr, head, diskSize, direction);
    }
}




