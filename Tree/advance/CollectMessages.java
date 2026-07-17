import java.util.*;

public class CollectMessages {
    static class Message {
        int id;
        int timestamp;
        String text;

        Message(int id, int timestamp, String text) {
            this.id = id;
            this.timestamp = timestamp;
            this.text = text;
        }
    }

    public static List<List<String>> organizeMessageThreads(List<Integer> timestamp, List<Integer> replyTo,
            List<String> messageText) {
        int n = timestamp.size();

        // Map to store children: ParentID -> List of direct child Messages
        Map<Integer, List<Message>> adj = new HashMap<>();
        List<Message> roots = new ArrayList<>();

        // Group messages by their parent
        for (int i = 0; i < n; i++) {
            Message msg = new Message(i, timestamp.get(i), messageText.get(i));
            if (replyTo.get(i) == -1) {
                roots.add(msg);
            } else {
                adj.computeIfAbsent(replyTo.get(i), k -> new ArrayList<>()).add(msg);
            }
        }

        // Rule: Threads must be ordered by the timestamp of their parent
        roots.sort(Comparator.comparingInt(m -> m.timestamp));

        List<List<String>> result = new ArrayList<>();

        // Process each thread starting from the root
        for (Message root : roots) {
            List<Message> threadMessages = new ArrayList<>();
            // Recursively collect all descendants (replies and replies-to-replies)
            collectAllDescendants(root, adj, threadMessages);

            // Rule: All messages in the thread must be sorted by timestamp
            threadMessages.sort(Comparator.comparingInt(m -> m.timestamp));

            List<String> threadTexts = new ArrayList<>();
            for (Message m : threadMessages) {
                threadTexts.add(m.text);
            }
            result.add(threadTexts);
        }

        return result;
    }

    // Helper to recursively collect all messages in a thread
    private static void collectAllDescendants(Message parent, Map<Integer, List<Message>> adj, List<Message> thread) {
        thread.add(parent);
        if (adj.containsKey(parent.id)) {
            for (Message child : adj.get(parent.id)) {
                collectAllDescendants(child, adj, thread);
            }
        }
    }
}
