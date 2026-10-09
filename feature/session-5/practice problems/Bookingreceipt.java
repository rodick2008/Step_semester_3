import java.util.Arrays;

public final class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        if (seatNumbers != null) {
            this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
        } else {
            this.seatNumbers = new String[0];
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= this.seatNumbers.length) {
            throw new IndexOutOfBoundsException("Invalid seat index");
        }
        String[] updatedSeats = Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
        updatedSeats[index] = newSeat;
        return new BookingReceipt(this.bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                if (receipt instanceof GroupBookingReceipt) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return String.format("%d processed | %d null skipped\n%d group | %d individual",
                processed, nullSkipped, groupCount, individualCount);
    }
}

class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
